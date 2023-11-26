package com.fluffy.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "NOTIFICATION_STOCK")
public class NotificationStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ns_id")
    private Long nsId;

    @Column(name = "ns_status")
    private String nsStatus;
    
    @Column(name = "ns_datetime")
    private Timestamp nsDatetime;
    
    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stocks stocks;
    
}
