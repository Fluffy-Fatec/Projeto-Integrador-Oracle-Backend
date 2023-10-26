package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
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
}
