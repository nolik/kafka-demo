package com.godeltech.com.kafkademo.configuration;

import com.godeltech.com.kafkademo.schema.Customer;
import com.godeltech.com.kafkademo.schema.PurchaseDetail;
import com.godeltech.com.kafkademo.service.PurchaseDetailJoiner;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import mysql.demo.purchase.Value;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TopologyConfiguration {

	@Bean
	public Topology buildTopology(KafkaConfiguration kafkaConfiguration,
		SpecificAvroSerde<PurchaseDetail> purchaseDetailSpecificAvroSerde,
		PurchaseDetailJoiner joiner) {
		val streamsBuilder = new StreamsBuilder();

		val customerTopic = kafkaConfiguration.getCustomerInputTopic();
		val rekeyedCustomerTopic = "rekeyed-customers";
		val purchaseTopic = kafkaConfiguration.getPurchaseInputTopic();
		val ratedMoviesTopic = kafkaConfiguration.getDetailOutputTopic();

		val customerStream = streamsBuilder.<String, Customer>stream(customerTopic)
			.map((key, customer) -> new KeyValue<>(String.valueOf(customer.getCustomerId()),
				customer));

		customerStream.to(rekeyedCustomerTopic);

		final KTable<String, Customer> customerTable = streamsBuilder.table(rekeyedCustomerTopic);

		val purchaseKStream = streamsBuilder.<String, Value>stream(purchaseTopic)
			.map((key, purchase) -> new KeyValue<>(String.valueOf(purchase.getId()), purchase));

		val purchaseDetailKStream = purchaseKStream.join(customerTable, joiner);

		purchaseDetailKStream
			.to(ratedMoviesTopic, Produced.with(Serdes.String(), purchaseDetailSpecificAvroSerde));

		val topology = streamsBuilder.build();

		// 6. Print topology.
		log.info(topology.describe().toString());

		return topology;
	}
}
