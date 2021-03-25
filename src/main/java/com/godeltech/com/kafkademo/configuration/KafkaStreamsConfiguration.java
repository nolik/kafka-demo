package com.godeltech.com.kafkademo.configuration;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.godeltech.com.kafkademo.exception.LogAndContinueDeserializationExceptionHandler;
import com.godeltech.com.kafkademo.exception.LogAndContinueProductionExceptionHandler;
import com.godeltech.com.kafkademo.schema.PurchaseDetail;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import java.util.HashMap;
import java.util.Properties;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaStreamsConfiguration {

	@Bean
	public KafkaStreams getKafkaStreams(Topology topology,
		@Qualifier("streams-configuration") Properties kafkaStreamsConfiguration) {
		return new KafkaStreams(topology, kafkaStreamsConfiguration);
	}

	@Bean("streams-configuration")
	public Properties getKafkaStreamsConfiguration(KafkaConfiguration kafkaConfiguration) {
		final var properties = new Properties();
		properties
			.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServers());
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaConfiguration.getApplicationId());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
			kafkaConfiguration.getAutoOffsetResetConfig());
		properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
		properties.put(SCHEMA_REGISTRY_URL_CONFIG, kafkaConfiguration.getSchemaRegistryUrl());
		properties.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.AT_LEAST_ONCE);
		properties.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
			LogAndContinueDeserializationExceptionHandler.class);
		properties.put(StreamsConfig.DEFAULT_PRODUCTION_EXCEPTION_HANDLER_CLASS_CONFIG,
			LogAndContinueProductionExceptionHandler.class);

		return properties;
	}


	@Bean
	public SpecificAvroSerde<PurchaseDetail> purchaseDetailSpecificAvroSerde(
		KafkaConfiguration kafkaConfiguration) {
		val movieAvroSerde = new SpecificAvroSerde<PurchaseDetail>();

		final HashMap<String, String> serdeConfig = new HashMap<>();
		serdeConfig.put(SCHEMA_REGISTRY_URL_CONFIG, kafkaConfiguration.getSchemaRegistryUrl());
		movieAvroSerde.configure(serdeConfig, false);

		return movieAvroSerde;
	}
}
