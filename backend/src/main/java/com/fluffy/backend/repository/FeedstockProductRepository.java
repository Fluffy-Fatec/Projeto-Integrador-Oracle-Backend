package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluffy.backend.entity.FeedstockProduct;

public interface FeedstockProductRepository  extends JpaRepository<FeedstockProduct, Long> {

}
