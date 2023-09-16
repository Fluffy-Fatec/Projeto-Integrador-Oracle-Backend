package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.ProductCommand;

@Repository
public interface ProductCommandRepository extends JpaRepository <ProductCommand, Integer> {

}
