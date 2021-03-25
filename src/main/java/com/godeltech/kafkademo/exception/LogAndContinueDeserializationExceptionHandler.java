package com.godeltech.kafkademo.exception;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;

@Slf4j
public class LogAndContinueDeserializationExceptionHandler implements
	DeserializationExceptionHandler {

	@Override
	public DeserializationHandlerResponse handle(final ProcessorContext context,
		final ConsumerRecord<byte[], byte[]> record,
		final Exception exception) {
		log.warn(
			"Exception caught during Deserialization, sending to the dead queue topic; taskId: {}, topic: {}, partition: {}, offset: {}",
			context.taskId(), record.topic(), record.partition(), record.offset(),
			exception);

		// Creating a producer out of the scope of KStreams means we do not get the concurrency guarantee.
		// We have to manage the consistency ourselves.
		// It is better to manage serialisation ourselves, and handle errors there.
		return DeserializationHandlerResponse.CONTINUE;
	}

	@Override
	public void configure(final Map<String, ?> map) {
		// Doesn't configure additional options coz Use Simple log logic.
	}
}
