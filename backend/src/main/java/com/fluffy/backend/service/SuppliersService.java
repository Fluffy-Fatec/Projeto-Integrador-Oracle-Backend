package com.fluffy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.SuppliersDTO;
import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.PaymentsMethodsRepository;
import com.fluffy.backend.repository.SuppliersRepository;

@Service
public class SuppliersService {

	@Autowired
	private SuppliersRepository suppliersRepository;

	@Autowired
	private PaymentsMethodsRepository paymentsMethodsRepository;

	public void saveSupplierWithPaymentsMethods(Suppliers supplier, PaymentsMethods paymentsMethods) {
		PaymentsMethods savedPaymentsMethods = paymentsMethodsRepository.save(paymentsMethods);
		
		supplier.setPaymentsMethods(savedPaymentsMethods);

		suppliersRepository.save(supplier);
	}

	public List<Suppliers> listSuppliersByName(String name) {
		return suppliersRepository.findByName(name);
	}

	public void deleteSuppliersPorId(Long id) {
		Suppliers suppliers = suppliersRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
		suppliersRepository.delete(suppliers);
	}

	public Suppliers updateSuppliers(Suppliers suppliers) {
		Long supplierId = suppliers.getIdSupplier();

		Suppliers existingSuppliers = suppliersRepository.findById(supplierId)
				.orElseThrow(() -> new IllegalArgumentException("Supplier not found"));

		existingSuppliers.setName(suppliers.getName());
		existingSuppliers.setSegment(suppliers.getSegment());
		existingSuppliers.setDeliveryForecast(suppliers.getDeliveryForecast());
		existingSuppliers.setCnpj(suppliers.getCnpj());
		existingSuppliers.setPhone(suppliers.getPhone());
		existingSuppliers.setAddress(suppliers.getAddress());
		existingSuppliers.setCity(suppliers.getCity());
		existingSuppliers.setState(suppliers.getState());
		existingSuppliers.setStatus(suppliers.getStatus());

		PaymentsMethods paymentsMethods = suppliers.getPaymentsMethods();
		if (paymentsMethods != null) {
			paymentsMethods = paymentsMethodsRepository.save(paymentsMethods);
			existingSuppliers.setPaymentsMethods(paymentsMethods);
		}

		return suppliersRepository.save(existingSuppliers);
	}
}
