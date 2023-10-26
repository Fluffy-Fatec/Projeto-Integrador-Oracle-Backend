package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluffy.backend.entity.Stocks;

public interface StocksRepository  extends JpaRepository<Stocks, Long>{

}
