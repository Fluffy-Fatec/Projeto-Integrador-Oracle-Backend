package com.fluffy.backend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
   // @SequenceGenerator(name = "seq_product", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
    @Column(name = "product_id")
    private Long idProduct;
    
    @Column(name = "product_name")
    private String name;
    
    @Column(name = "product_value")
    private BigDecimal value;
    
    @Column(name = "product_type")
    private String type;
    
}