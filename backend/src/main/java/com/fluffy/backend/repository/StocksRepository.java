package com.fluffy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fluffy.backend.DTO.StocksAndSupplierStockOfferDTO;
import com.fluffy.backend.entity.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, Long> {

	@Query("SELECT s, so FROM Stocks s LEFT JOIN SupplierStockOffer so ON s.IdFeedstock = so.stocks.IdFeedstock WHERE s.name = :name")
	List<StocksAndSupplierStockOfferDTO> findStocksAndSupplierStockOfferByName(@Param("name") String name);
}
