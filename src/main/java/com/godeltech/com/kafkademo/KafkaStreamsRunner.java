package com.godeltech.com.kafkademo;

import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaStreamsRunner implements ApplicationRunner {

	private final KafkaStreams streams;
	private final UncaughtExceptionHandler uncaughtExceptionHandler;

	@Override
	public void run(ApplicationArguments args) {
		log.info("{} starting...", KafkaStreamsRunner.class.getName());

		streams.setUncaughtExceptionHandler(uncaughtExceptionHandler);

		// For development mod only:
		streams.cleanUp();
		streams.start();

		// Graceful shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			log.error("Application shutting down, closing streams.");
			// The timeout is needed because on some errors the Kafka streams application will not close successfully
			// An example of such a case is when the input topic does not exist
			streams.close(Duration.ofSeconds(60));
		}));
	}
}
