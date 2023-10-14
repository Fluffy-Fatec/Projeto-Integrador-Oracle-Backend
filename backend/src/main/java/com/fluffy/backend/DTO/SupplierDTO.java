package com.fluffy.backend.DTO;

import java.sql.Timestamp;

import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Suppliers;

import lombok.Data;

@Data
public class SupplierDTO {
	private String name;
	private String segment;
	private String deliveryForecast;
	private String cnpj;
	private Long phone;
	private String address;
	private String city;
	private String state;
	private String status;
	private String paymentMethodName;
	private Timestamp paymentMethodPayDay;
}
