package com.godeltech.com.kafkademo.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogAndShutdownUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	private final ApplicationContext context;

	@Override
	public void uncaughtException(final Thread t, final Throwable e) {
		log.error("Uncaught exception in thread {}: {}", t, e);

		int exitCode = SpringApplication.exit(context, () -> 1);
		System.exit(exitCode);
	}
}
