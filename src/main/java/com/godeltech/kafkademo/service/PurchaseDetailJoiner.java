package com.godeltech.kafkademo.service;


import godel.demo.Customer;
import godel.demo.PurchaseDetail;
import godel.demo.purchase.Value;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDetailJoiner implements
	ValueJoiner<Value, Customer, PurchaseDetail> {

	@Override
	public PurchaseDetail apply(godel.demo.purchase.Value purchase,
		godel.demo.Customer customer) {
		return godel.demo.PurchaseDetail.newBuilder()
			.setId(purchase.getId())
			.setFirstName(customer.getFirstName())
			.setSecondName(customer.getSecondName())
			.setProduct(purchase.getProduct())
			.setPrice(purchase.getPrice())
			.setOccurredAt(purchase.getUPDATETS())
			.build();
	}
}

