package com.fluffy.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStock;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockRepository;

@Service
public class StocksService {
	
	@Autowired
	StocksRepository feedstocksRepository;
	
	@Autowired
	SupplierStockRepository supplierFeedstockRepository;
	
	 public List<StocksListDTO> getAllFeedstocks() {
		 
		 List<StocksListDTO> feedstocksListDTO = new ArrayList<>();
		 
		 for(Stocks feedstock:feedstocksRepository.findAll()) {
			 StocksListDTO feedstocksDTO = new StocksListDTO();
			 
			 SupplierStock supplierFeedstock  = supplierFeedstockRepository.findByStocks(feedstock);
			 
			 feedstocksDTO.setIdFeedstock(feedstock.getIdFeedstock());
			 feedstocksDTO.setName(feedstock.getName());
			 feedstocksDTO.setAmountAvailable(feedstock.getAmountAvailable());
			 feedstocksDTO.setMeasurement(feedstock.getMeasurement());
			 feedstocksDTO.setPrice(supplierFeedstock.getPrice());
			 feedstocksListDTO.add(feedstocksDTO);
		 }
	        return feedstocksListDTO;
	    }
}