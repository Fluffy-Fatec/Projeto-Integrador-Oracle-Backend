package com.fluffy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fluffy.backend.entity.Feedstocks;
import com.fluffy.backend.repository.FeedstocksRepository;

public class FeedstocksServiceTest {
	@InjectMocks
	private FeedstocksService feedstocksService;

	@Mock
	private FeedstocksRepository feedstocksRepository;

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

		when(feedstocksRepository.findAll()).thenReturn(feedstocksList);

		List<Feedstocks> result = feedstocksService.getAllFeedstocks();

		assertEquals(2, result.size()); // Verifique o tamanho da lista
		assertEquals("Feedstock1", result.get(0).getName()); // Verifique o nome do primeiro feedstock
		assertEquals(100.0, result.get(0).getAmountAvailable()); // Verifique a quantidade disponível do primeiro
																	// feedstock
		assertEquals("Kg", result.get(0).getMeasurement()); // Verifique a unidade de medida do primeiro feedstock
	}
}
