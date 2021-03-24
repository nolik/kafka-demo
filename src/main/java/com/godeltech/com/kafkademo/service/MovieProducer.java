package com.godeltech.com.kafkademo.service;

import com.godeltech.com.kafkademo.avro.Movie;
import com.godeltech.com.kafkademo.configuration.KafkaConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
@RequiredArgsConstructor
public class MovieProducer {

	private final KafkaConfiguration kafkaConfiguration;
	private final KafkaProducer<String, Movie> kafkaProducer;

	public void sendMessage(Movie movie) {
		final var record =
			new ProducerRecord<>(kafkaConfiguration.getMovieInputTopic(), String.valueOf(movie.getId()), movie);

		kafkaProducer.send(record, (metadata, exception) -> {
			if (exception != null) {
				log.warn("Send failed for record {}", exception);
			} else {
				log.info(String.format("Send succeeded for record=%s", record));
			}
		});
	}
}
