package com.fluffy.backend.DTO;

import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Suppliers;

import lombok.Data;

@Data
public class SuppliersDTO {

	private Suppliers suppliers;
	private PaymentsMethods paymentsMethods;

}
