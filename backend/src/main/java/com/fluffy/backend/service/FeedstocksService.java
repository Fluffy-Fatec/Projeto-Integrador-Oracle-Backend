package com.fluffy.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.FeedstocksListDTO;
import com.fluffy.backend.entity.Feedstocks;
import com.fluffy.backend.entity.SupplierFeedstock;
import com.fluffy.backend.entity.SupplierFeedstockOffer;
import com.fluffy.backend.repository.FeedstocksRepository;
import com.fluffy.backend.repository.SupplierFeedstockRepository;

@Service
public class FeedstocksService {
	
	@Autowired
	FeedstocksRepository feedstocksRepository;
	
	@Autowired
	SupplierFeedstockRepository supplierFeedstockRepository;
	
	 public List<FeedstocksListDTO> getAllFeedstocks() {
		 
		 List<FeedstocksListDTO> feedstocksListDTO = new ArrayList<>();
		 
		 for(Feedstocks feedstock:feedstocksRepository.findAll()) {
			 FeedstocksListDTO feedstocksDTO = new FeedstocksListDTO();
			 
			 SupplierFeedstock supplierFeedstock  = supplierFeedstockRepository.findByFeedstocks(feedstock);
			 
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
