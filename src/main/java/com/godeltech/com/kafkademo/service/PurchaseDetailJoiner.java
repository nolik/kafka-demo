package com.godeltech.com.kafkademo.service;


import com.godeltech.com.kafkademo.schema.Customer;
import com.godeltech.com.kafkademo.schema.PurchaseDetail;
import mysql.demo.purchase.Value;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDetailJoiner implements ValueJoiner<Value, Customer, PurchaseDetail> {

	@Override
	public PurchaseDetail apply(Value purchase, Customer customer) {
		return PurchaseDetail.newBuilder()
			.setId(purchase.getId())
			.setFirstName(customer.getFirstName())
			.setSecondName(customer.getSecondName())
			.setProduct(purchase.getProduct())
			.setPrice(purchase.getPrice())
			.setOccurredAt(purchase.getUPDATETS())
			.build();
	}
}

