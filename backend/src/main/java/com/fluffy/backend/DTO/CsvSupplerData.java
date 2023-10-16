package com.fluffy.backend.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CsvSupplerData {
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
	private String feedName;
	private Double amountAvailable;
	private String feedMeasurement;
	private Double quantityCan;
	private String measurement;
	private BigDecimal value;

}
