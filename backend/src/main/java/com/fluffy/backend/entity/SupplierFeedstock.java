package com.fluffy.backend.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.function.Supplier;

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
@Table(name = "Supplier_Feedstock")
public class SupplierFeedstock {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sf_id")
    private Long IdSupplierFeedstock;

    @Column(name = "date_arrive", nullable = false)
    private Timestamp dateArrive;

    @Column(name = "date_solicitation", nullable = false)
    private Timestamp dateSolicitation;

    @Column(name = "sf_price", nullable = false)
    private Double price;

    @Column(name = "sf_date_feedstock_end", nullable = false)
    private Timestamp dateFeedstockEnd;

    @Column(name = "sf_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Suppliers suppliers;

    @ManyToOne
    @JoinColumn(name = "feedstock_id")
    private Feedstocks feedstocks;

    @Column(name = "quantity_supplied", nullable = false)
    private Double quantitySupplied;

    @Column(name = "quantity_solicited", nullable = false)
    private Double quantitySolicited;
   

    @Column(name = "pc_measurement", nullable = false)
    private String measurement;

}
