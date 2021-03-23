package com.godeltech.com.kafkademo;

import com.godeltech.com.kafkademo.avro.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerRunner implements ApplicationRunner {
	private final Producer producer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		producer.sendMessage(Movie.newBuilder()
			.clearId().setId(1)
			.clearTitle().setTitle("Die Hard")
			.clearReleaseYear().setReleaseYear(1992)
			.build());
		producer.sendMessage(Movie.newBuilder()
			.clearId().setId(2)
			.clearTitle().setTitle("Die Hard2")
			.clearReleaseYear().setReleaseYear(1994)
			.build());
		producer.sendMessage(Movie.newBuilder()
			.clearId().setId(3)
			.clearTitle().setTitle("Die Hard3")
			.clearReleaseYear().setReleaseYear(1996)
			.build());
	}
}
