package com.fluffy.backend.controlle
/;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.DTO.StocksAndSupplierStockOfferDTO;
import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.service.StocksService;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks")
public class StocksController {
	
	@Autowired
	StocksService feedstocksService;

	@GetMapping
	public List<StocksListDTO> listAllFeedstocks() {
		return feedstocksService.getAllFeedstocks();
	}
	
	@GetMapping("/StockOffer")
	public List<SupplierStockOffer> listAllSupplierStockOffer() {
		return feedstocksService.getAllSupplierStockOffer();
	}
	
	 @GetMapping("/{name}")
	    public ResponseEntity<List<StocksAndSupplierStockOfferDTO>> findStocksAndSupplierStockOfferByName(@PathVariable String name) {
	        List<StocksAndSupplierStockOfferDTO> result = feedstocksService.findStocksAndSupplierStockOfferByName(name);
	        return ResponseEntity.ok(result);
	    }
	
}
