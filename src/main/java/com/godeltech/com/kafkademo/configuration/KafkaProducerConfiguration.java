package com.godeltech.com.kafkademo.configuration;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import com.godeltech.com.kafkademo.avro.Movie;
import com.godeltech.com.kafkademo.avro.Rating;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lombok.Getter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KafkaProducerConfiguration {

	@Value("${kafka.producer.transaction-quantity}")
	private Integer transactionQuantity;

	@Value("${kafka.producer.transaction-amount-limit}")
	private Integer transactionAmountLimit;

	@Value("${kafka.producer.available-usernames}")
	private String[] availableUserNames;

	@Bean
	public KafkaProducer<String, Movie> getMovieKafkaProducer(
		final KafkaConfiguration kafkaConfiguration) {

		return new KafkaProducer<>(Map.of(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServers(),
			ProducerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString(),
			ProducerConfig.ACKS_CONFIG, kafkaConfiguration.getAcks(),
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName(),
			SCHEMA_REGISTRY_URL_CONFIG, kafkaConfiguration.getSchemaRegistryUrl()));
	}

	@Bean
	public KafkaProducer<String, Rating> getRatingKafkaProducer(
		final KafkaConfiguration kafkaConfiguration) {

		return new KafkaProducer<>(Map.of(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServers(),
			ProducerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString(),
			ProducerConfig.ACKS_CONFIG, kafkaConfiguration.getAcks(),
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName(),
			SCHEMA_REGISTRY_URL_CONFIG, kafkaConfiguration.getSchemaRegistryUrl()));
	}

	@Bean
	public Random getRandom() {
		return new Random();
	}
}
