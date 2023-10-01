package com.prices.demo.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepositoryJpa extends JpaRepository<PriceDataBaseEntity, Integer> {

  @Query("SELECT p FROM PriceDataBaseEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN startDate AND endDate")
  public List<PriceDataBaseEntity> findPrices(
      @Param("productId") String productId,
      @Param("brandId") int brandId,
      @Param("applicationDate") LocalDateTime applicationDate);

  public List<PriceDataBaseEntity> findAll();
}
