package com.godeltech.kafkademo;

import com.github.javafaker.Faker;
import com.godeltech.kafkademo.service.CustomerProducer;
import godel.demo.Customer;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
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
			.map(this::createNewCustomer)
			.forEach(this::produceNewCustomer);
	}

	private Customer createNewCustomer(int customerId) {
		val fakeName = new Faker().name();

		return Customer.newBuilder()
			.setCustomerId(customerId)
			.setFirstName(fakeName.firstName())
			.setSecondName(fakeName.lastName())
			.build();
	}

	private void produceNewCustomer(Customer customer) {
		customerProducer.sendMessage(customer);
	}
}
