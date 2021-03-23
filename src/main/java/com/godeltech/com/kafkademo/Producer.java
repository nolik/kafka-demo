package com.godeltech.com.kafkademo;

import com.godeltech.com.kafkademo.avro.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
@RequiredArgsConstructor
public class Producer {

	private final KafkaTemplate<String, Movie> kafkaTemplate;
	private String TOPIC = "movies";



	void sendMessage(Movie movie) {
		this.kafkaTemplate.send(this.TOPIC, String.valueOf(movie.getId()), movie);
		log.info(String.format("Produced movie -> %s", movie));
	}
}
