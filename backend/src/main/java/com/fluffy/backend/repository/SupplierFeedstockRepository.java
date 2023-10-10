package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.SupplierFeedstock;

@Repository
public interface SupplierFeedstockRepository extends JpaRepository<SupplierFeedstock, Long>{

}
