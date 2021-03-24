package com.godeltech.com.kafkademo.configuration;

import com.godeltech.com.kafkademo.avro.Movie;
import com.godeltech.com.kafkademo.avro.RatedMovie;
import com.godeltech.com.kafkademo.avro.Rating;
import com.godeltech.com.kafkademo.service.MovieRatingJoiner;
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
		SpecificAvroSerde<RatedMovie> ratedMovieAvroSerde, MovieRatingJoiner joiner) {
		val streamsBuilder = new StreamsBuilder();
		val movieTopic = kafkaConfiguration.getMovieInputTopic();
		val rekeyedMovieTopic = "rekeyed-movies";
		val ratingTopic = kafkaConfiguration.getRatingInputTopic();
		val ratedMoviesTopic = kafkaConfiguration.getOutputTopic();

		val movieStream = streamsBuilder.<String, Movie>stream(movieTopic)
			.map((key, movie) -> new KeyValue<>(String.valueOf(movie.getId()), movie));

		movieStream.to(rekeyedMovieTopic);

		final KTable<String, Movie> movies = streamsBuilder.table(rekeyedMovieTopic);

		val ratings = streamsBuilder.<String, Rating>stream(ratingTopic)
			.map((key, rating) -> new KeyValue<>(String.valueOf(rating.getId()), rating));

		val ratedMovie = ratings.join(movies, joiner);

		ratedMovie.to(ratedMoviesTopic, Produced.with(Serdes.String(), ratedMovieAvroSerde));

		val topology = streamsBuilder.build();

		// 6. Print topology.
		log.info(topology.describe().toString());

		return topology;
	}
}
