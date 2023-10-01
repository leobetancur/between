package com.prices.demo.infrastructure.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "prices")
@Data
@ToString
public class PriceDataBaseEntity {

  @Id
  private int id;

  @Column(name = "brand_id")
  private int brandId;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Column(name = "price_list")
  private long priceList;

  @Column(name = "product_id")
  private String productId;

  @Column(name = "priority")
  private int priority;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "curr")
  private String currency;
}

