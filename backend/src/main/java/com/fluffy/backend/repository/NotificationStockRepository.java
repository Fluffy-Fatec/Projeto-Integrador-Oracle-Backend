package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.NotificationStock;

@Repository

public interface NotificationStockRepository extends JpaRepository<NotificationStock, Long> {

}
