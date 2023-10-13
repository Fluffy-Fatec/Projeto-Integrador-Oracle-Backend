package com.fluffy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.entity.Feedstocks;
import com.fluffy.backend.repository.FeedstocksRepository;

@Service
public class FeedstocksService {
	
	@Autowired
	FeedstocksRepository feedstocksRepository;
	
	 public List<Feedstocks> getAllFeedstocks() {
	        return feedstocksRepository.findAll();
	    }
}
