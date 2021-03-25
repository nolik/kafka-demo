package com.godeltech.com.kafkademo.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfiguration {
	private final String applicationId;
	private final String bootstrapServers;
	private final Integer retries;
	private final String acks;
	private final String autoOffsetResetConfig;
	private final String schemaRegistryUrl;

	private final String customerInputTopic;
	private final String purchaseInputTopic;
	private final String detailOutputTopic;
}
