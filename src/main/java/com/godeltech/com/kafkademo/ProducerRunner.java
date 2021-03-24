package com.godeltech.com.kafkademo;

import com.godeltech.com.kafkademo.avro.Movie;
import com.godeltech.com.kafkademo.avro.Rating;
import com.godeltech.com.kafkademo.service.MovieProducer;
import com.godeltech.com.kafkademo.service.RatingProducer;
import java.util.Random;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
//		Stream.iterate(0, i -> i + 1)
//			.limit(1000)
//			.map(this::newPair)
//			.peek(this::pushTransactionToKafka)
//			.forEach(System.out::println);


		Stream.iterate(10000, i -> i + 1)
			.limit(100)
			.map(this::newMovie)
			.forEach(this::pushTransactionToKafka);

		Stream.iterate(10050, i -> i + 1)
			.limit(100)
			.map(this::newRating)
			.forEach(this::pushTransactionToKafka);
	}

	@SneakyThrows
	private void pushTransactionToKafka(Movie movie) {
		movieProducer.sendMessage(movie);
		Thread.sleep(300l);
	}

	@SneakyThrows
	private void pushTransactionToKafka(Rating rating) {
		ratingProducer.sendMessage(rating);
		Thread.sleep(300l);
	}

	@SneakyThrows
	private void pushTransactionToKafka(ImmutablePair<Movie, Rating> tuple){
		movieProducer.sendMessage(tuple.left);
		Thread.sleep(1000l);
		ratingProducer.sendMessage(tuple.right);
	}

	private ImmutablePair<Movie, Rating> newPair(int i) {
		val rating = newRating(i + random.nextInt(3));
		val movie = newMovie(i);

		return ImmutablePair.of(movie, rating);
	}

	private Movie newMovie(int i) {
		return Movie.newBuilder()
			.setId(i)
			.setTitle("Die Hard " + i)
			.setReleaseYear(1992 + i)
			.build();
	}

	private Rating newRating(int i) {
		return Rating.newBuilder()
			.setId(i)
			.setRating(random.nextDouble())
			.build();
	}
}
