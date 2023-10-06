package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long>{

}
