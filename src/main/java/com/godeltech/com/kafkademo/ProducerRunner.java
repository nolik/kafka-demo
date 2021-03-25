package com.godeltech.com.kafkademo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.godeltech.com.kafkademo.schema.Customer;
import com.godeltech.com.kafkademo.service.CustomerProducer;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerRunner implements ApplicationRunner {

	private final CustomerProducer customerProducer;

	@Override
	public void run(ApplicationArguments args) {

		Stream.iterate(1, i -> i + 1)
			.limit(300)
			.map(this::newCustomer)
			.forEach(this::pushTransactionToKafka);
	}

	@SneakyThrows
	private void pushTransactionToKafka(Customer customer) {
		customerProducer.sendMessage(customer);
//		Thread.sleep(300l);
	}

	private Customer newCustomer(int i) {
		Name fakeName = new Faker().name();

		return Customer.newBuilder()
			.setCustomerId(i)
			.setFirstName(fakeName.firstName())
			.setSecondName(fakeName.lastName())
			.build();
	}
}
