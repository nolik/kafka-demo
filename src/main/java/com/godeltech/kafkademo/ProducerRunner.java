package com.godeltech.kafkademo;

import com.godeltech.kafkademo.service.CustomerProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommonsLog(topic = "ProducerRunner")
public class ProducerRunner implements ApplicationRunner {

	private final CustomerProducer customerProducer;

	@Override
	public void run(ApplicationArguments args) {
		log.info("Starting to produce customer");
		customerProducer.produceNewCustomers(300);
		log.info("Finish to produce customer");
	}
}
