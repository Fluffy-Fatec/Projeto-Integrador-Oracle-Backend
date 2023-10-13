package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.entity.Feedstocks;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.service.FeedstocksService;

@CrossOrigin
@RestController
@RequestMapping("/api/feedstocks")
public class FeedstocksController {
	
	@Autowired
	FeedstocksService feedstocksService;

	@GetMapping
	public List<Feedstocks> listAllFeedstocks() {
		return feedstocksService.getAllFeedstocks();
	}
}
