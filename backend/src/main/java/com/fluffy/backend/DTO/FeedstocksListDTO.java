package com.fluffy.backend.DTO;

import lombok.Data;

@Data
public class FeedstocksListDTO {

	private Long IdFeedstock;
	
	private String name;
	
	private Double amountAvailable;
	
	private String measurement;
	
	private Double price;
}
