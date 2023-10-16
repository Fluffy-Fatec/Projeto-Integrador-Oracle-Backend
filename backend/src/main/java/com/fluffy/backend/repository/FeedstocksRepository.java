package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluffy.backend.entity.Feedstocks;

public interface FeedstocksRepository  extends JpaRepository<Feedstocks, Long>{

}
