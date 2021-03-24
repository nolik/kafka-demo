package com.godeltech.com.kafkademo;

import com.godeltech.com.kafkademo.avro.Movie;
import com.godeltech.com.kafkademo.avro.Rating;
import java.util.Random;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerRunner implements ApplicationRunner {

	private final MovieProducer movieProducer;
	private final RatingProducer ratingProducer;
	private final Random random;

	@Override
	public void run(ApplicationArguments args) {
		Stream.iterate(0, i -> i + 1)
			.map(this::newMovie)
			.peek(this::pushTransactionToKafka)
			.forEach(System.out::println);

		Stream.iterate(0, i -> i + 1)
			.map(this::newRating)
			.peek(this::pushTransactionToKafka)
			.forEach(System.out::println);
	}

	private void pushTransactionToKafka(Movie movie) {
		movieProducer.sendMessage(movie);
	}

	private void pushTransactionToKafka(Rating rating) {
		ratingProducer.sendMessage(rating);
	}

	private Movie newMovie(int i) {
		return Movie.newBuilder()
			.setId(i)
			.setTitle("Die Hard")
			.setReleaseYear(1992)
			.build();
	}

	private Rating newRating(int i) {
		return Rating.newBuilder()
			.setId(i)
			.setRating(random.nextDouble())
			.build();
	}
}
