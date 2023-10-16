package com.fluffy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fluffy.backend.DTO.FeedstocksListDTO;
import com.fluffy.backend.entity.Feedstocks;
import com.fluffy.backend.entity.SupplierFeedstock;
import com.fluffy.backend.repository.FeedstocksRepository;
import com.fluffy.backend.repository.SupplierFeedstockRepository;

public class FeedstocksServiceTest {
	@InjectMocks
	private FeedstocksService feedstocksService;

	@Mock
	private FeedstocksRepository feedstocksRepository;

	@Mock
	private SupplierFeedstockRepository supplierFeedstockRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllFeedstocks() {
		
		List<Feedstocks> feedstocksList = new ArrayList<>();

		Feedstocks feedstock1 = new Feedstocks();
		feedstock1.setName("Feedstock1");
		feedstock1.setAmountAvailable(100.0);
		feedstock1.setMeasurement("Kg");

		Feedstocks feedstock2 = new Feedstocks();
		feedstock2.setName("Feedstock2");
		feedstock2.setAmountAvailable(50.0);
		feedstock2.setMeasurement("Liters");

		feedstocksList.add(feedstock1);
		feedstocksList.add(feedstock2);

		SupplierFeedstock supplierfeedstock = new SupplierFeedstock();
		supplierfeedstock.setPrice(Double.valueOf("50"));
		
		when(feedstocksRepository.findAll()).thenReturn(feedstocksList);
		when(supplierFeedstockRepository.findByFeedstocks(Mockito.any())).thenReturn(supplierfeedstock);
		
		
		List<FeedstocksListDTO> feedstocksListDTO = feedstocksService.getAllFeedstocks();

		assertEquals(2, feedstocksListDTO.size()); // Verifique o tamanho da lista
		assertEquals("Feedstock1", feedstocksListDTO.get(0).getName()); // Verifique o nome do primeiro feedstock
		assertEquals(100.0, feedstocksListDTO.get(0).getAmountAvailable()); // Verifique a quantidade disponível do primeiro
																	// feedstock
		assertEquals("Kg", feedstocksListDTO.get(0).getMeasurement()); // Verifique a unidade de medida do primeiro feedstock
	}
}
