package com.fluffy.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Suppliers")
public class Suppliers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long IdSupplier;

    @Column(name = "supplier_name", nullable = false)
    private String name;

    @Column(name = "supplier_segment", nullable = false)
    private String segment;

    @Column(name = "delivery_forecast", nullable = false)
    private String deliveryForecast;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "phone_01", nullable = false)
    private Long phone01;

    @Column(name = "phone_02", nullable = false)
    private Long phone02;

    @Column(name = "address", nullable = false)
    private String address;
    
	@ManyToOne
	@JoinColumn(name = "pm_id")
	private PaymentsMethods paymentsMethods;
}
