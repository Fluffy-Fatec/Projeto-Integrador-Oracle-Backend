package com.fluffy.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fluffy.backend.DTO.CsvSupplerData;
import com.fluffy.backend.entity.Feedstocks;
import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.SupplierFeedstockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.FeedstocksRepository;
import com.fluffy.backend.repository.PaymentsMethodsRepository;
import com.fluffy.backend.repository.SupplierFeedstockOfferRepository;
import com.fluffy.backend.repository.SuppliersRepository;

@SpringBootTest
public class CsvSupplierServiceTest {
	
	@InjectMocks
	private CsvSupplierService csvSupplierService;

	@Mock
	private SuppliersRepository suppliersRepository;

	@Mock
	private PaymentsMethodsRepository paymentsMethodsRepository;

	@Mock
	private SupplierFeedstockOfferRepository supplierFeedstockOfferRepository;

	@Mock
	private FeedstocksRepository feedstocksRepository;


	@Test
	public void testProcessCsvData() throws IOException {
		List<CsvSupplerData> csvDataList = new ArrayList<>();
		CsvSupplerData csvData = new CsvSupplerData();

		csvData.setName("Company A");
		csvData.setSegment("Segment A");
		csvData.setDeliveryForecast("2023-10-14");
		csvData.setCnpj("1234567890");
		csvData.setPhone((long) 123456789);
		csvData.setAddress("Address A");
		csvData.setCity("City A");
		csvData.setState("State A");
		csvData.setStatus("Active");
		csvData.setPaymentMethodName("PaymentMethodA");
		csvData.setPaymentMethodPayDay(Timestamp.valueOf("2023-10-14 12:00:00"));
		csvData.setFeedName("FeedA");
		csvData.setAmountAvailable(100.0);
		csvData.setFeedMeasurement("kg");
		csvData.setQuantityCan(50.0);
		csvData.setMeasurement("liters");
		csvData.setValue(new BigDecimal("500.50"));

		csvDataList.add(csvData);

		when(suppliersRepository.saveAndFlush(any(Suppliers.class))).thenReturn(new Suppliers());
		when(paymentsMethodsRepository.saveAndFlush(any(PaymentsMethods.class))).thenReturn(new PaymentsMethods());
		when(feedstocksRepository.saveAndFlush(any(Feedstocks.class))).thenReturn(new Feedstocks());
		when(supplierFeedstockOfferRepository.saveAndFlush(any(SupplierFeedstockOffer.class)))
				.thenReturn(new SupplierFeedstockOffer());

		csvSupplierService.processCsvData(csvDataList);

		verify(suppliersRepository, times(1)).saveAndFlush(any(Suppliers.class));
		verify(paymentsMethodsRepository, times(1)).saveAndFlush(any(PaymentsMethods.class));
		verify(feedstocksRepository, times(1)).saveAndFlush(any(Feedstocks.class));
		verify(supplierFeedstockOfferRepository, times(1)).saveAndFlush(any(SupplierFeedstockOffer.class));
	}

}
