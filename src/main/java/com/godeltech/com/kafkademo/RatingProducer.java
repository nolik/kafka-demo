package com.godeltech.com.kafkademo;

import com.godeltech.com.kafkademo.avro.Rating;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
@RequiredArgsConstructor
public class RatingProducer {

	private final KafkaProducer<String, Rating> kafkaProducer;
	private String TOPIC = "ratings";

	void sendMessage(Rating movie) {
		final var record =
			new ProducerRecord<>(this.TOPIC, String.valueOf(movie.getId()), movie);

		kafkaProducer.send(record, (metadata, exception) -> {
			if (exception != null) {
				log.warn("Send failed for record {}", exception);
			} else {
				log.info("Send succeeded for record");
			}
		});
	}
}
