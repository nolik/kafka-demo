package com.godeltech.com.kafkademo.exceptions;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.errors.ProductionExceptionHandler;

/*
  Martech do not use this.
 */
@Slf4j
public class LogAndContinueProductionExceptionHandler implements ProductionExceptionHandler {

	@Override
	public ProductionExceptionHandlerResponse handle(final ProducerRecord<byte[], byte[]> record,
		final Exception exception) {
		log.error("Exception while producing to Kafka", exception);
		return ProductionExceptionHandlerResponse.CONTINUE;
	}

	@Override
	public void configure(final Map<String, ?> map) {
		// Doesn't configure additional options coz Use Simple log logic.
	}
}
