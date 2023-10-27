package com.fluffy.backend.DTO;

import lombok.Data;

@Data
public class StocksListDTO {

	private Long IdFeedstock;
	
	private String name;
	
	private Double amountAvailable;
	
	private String measurement;
	
	private Double price;
}
