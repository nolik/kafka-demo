package com.godeltech.kafkademo.configuration;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import godel.demo.Customer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KafkaProducerConfiguration {

	@Bean
	public KafkaProducer<String, Customer> getCustomerKafkaProducer(
		KafkaConfiguration kafkaConfiguration) {

		return new KafkaProducer<>(Map.of(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServers(),
			ProducerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString(),
			ProducerConfig.ACKS_CONFIG, kafkaConfiguration.getAcks(),
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName(),
			SCHEMA_REGISTRY_URL_CONFIG, kafkaConfiguration.getSchemaRegistryUrl()));
	}
}
