package com.fluffy.backend.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Feedstock_Product")
public class FeedstockProduct {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long IdFeedstockProduct;

    @Column(name = "datetime_ready", nullable = false)
    private Timestamp datetimeReady;

    @Column(name = "fp_amount_contained", nullable = false)
    private Double amountContained;

    @ManyToOne
    @JoinColumn(name = "feedstock_id", nullable = false)
    private Feedstocks feedstocks;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "pc_measurement", nullable = false)
    private String measurement;
}
