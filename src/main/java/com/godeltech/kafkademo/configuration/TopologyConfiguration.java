package com.godeltech.kafkademo.configuration;

import com.godeltech.kafkademo.service.PurchaseDetailJoiner;
import godel.demo.purchase.Value;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
		SpecificAvroSerde<godel.demo.PurchaseDetail> purchaseDetailSpecificAvroSerde,
		PurchaseDetailJoiner joiner) {
		val streamsBuilder = new StreamsBuilder();
		val customerTopic = kafkaConfiguration.getCustomerInputTopic();
		val purchaseTopic = kafkaConfiguration.getPurchaseInputTopic();
		val purchaseDetailTopic = kafkaConfiguration.getDetailOutputTopic();

		// Create table of Customers
		final KTable<String, godel.demo.Customer> customerTable = streamsBuilder
			.table(customerTopic);

		// Create stream of purchases
		val purchaseKStream = streamsBuilder.<String, Value>stream(purchaseTopic)
			.map((key, purchase) -> new KeyValue<>(String.valueOf(purchase.getCustomerId()),
				purchase));
		
		// Join stream of purchases with customer information from KTable
		val purchaseDetailKStream = purchaseKStream.join(customerTable, joiner);

		// Produce resulted stream to output topic
		purchaseDetailKStream
			.to(purchaseDetailTopic, Produced.with(Serdes.String(), purchaseDetailSpecificAvroSerde));

		val topology = streamsBuilder.build();

		// Print topology.
		log.info(topology.describe().toString());

		return topology;
	}
}
