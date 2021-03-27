package com.godeltech.kafkademo.service;

import com.github.javafaker.Faker;
import com.godeltech.kafkademo.configuration.KafkaConfiguration;
import godel.demo.Customer;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
@RequiredArgsConstructor
public class CustomerProducer {

	private final KafkaConfiguration kafkaConfiguration;
	private final KafkaProducer<String, Customer> kafkaProducer;
	
	public void produceNewCustomers(int numberOfCustomers){
		Stream.iterate(1, i -> i + 1)
			.limit(numberOfCustomers)
			.map(this::createNewCustomer)
			.forEach(this::sendMessage);

		kafkaProducer.close();
	}

	private Customer createNewCustomer(int customerId) {
		val fakeName = new Faker().name();

		return Customer.newBuilder()
			.setCustomerId(customerId)
			.setFirstName(fakeName.firstName())
			.setSecondName(fakeName.lastName())
			.build();
	}
	
	private void sendMessage(Customer customer) {
		val record = new ProducerRecord<>(kafkaConfiguration.getCustomerInputTopic(),
			String.valueOf(customer.getCustomerId()), customer);

		kafkaProducer.send(record, (metadata, exception) -> {
			if (exception != null) {
				log.warn("Send failed for record {}", exception);
			} else {
				log.info(String.format("Send succeeded for record=%s", record));
			}
		});
	}
}
