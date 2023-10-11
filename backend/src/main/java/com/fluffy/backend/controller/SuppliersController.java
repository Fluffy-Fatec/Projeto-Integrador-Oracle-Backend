package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.DTO.SuppliersDTO;
import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.service.SuppliersService;


@CrossOrigin
@RestController
@RequestMapping("/api/suppliers")
public class SuppliersController {

	@Autowired
	private SuppliersService suppliersService;

	@PostMapping
	public ResponseEntity<String> createSupplierWithPaymentsMethods(@RequestBody Suppliers supplier,
			@RequestBody PaymentsMethods paymentsMethods) {
		
		suppliersService.saveSupplierWithPaymentsMethods(supplier, paymentsMethods);
		return ResponseEntity.ok("Fornecedor e Método de Pagamento salvos com sucesso.");
	}

	@GetMapping("/{name}")
	public List<Suppliers> listSuppliersByName(@PathVariable String name) {
		return suppliersService.listSuppliersByName(name);
	}

	@DeleteMapping("/{id}")
	public void deleteSupplier(@PathVariable Long id) {
		suppliersService.deleteSuppliersPorId(id);
	}

	@PutMapping("/update")
	public Suppliers updateSupplier(@RequestBody Suppliers suppliers) {
		return suppliersService.updateSuppliers(suppliers);
	}

}
