package com.fluffy.backend.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class CsvData {


	private String productName;
	private BigDecimal productValue;
	private String productType;
	private BigDecimal pcQuantity;
	private String pcMeasurement;
	private Integer pcTurn;
	private Integer commandNumber;
	private Timestamp pcDatetimeOrder;

}
