package com.prices.demo.infrastructure.controller.output;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceDto {

  private String productId;

  private int brandId;

  private long priceList;

  @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
  private LocalDateTime startDate;

  @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
  private LocalDateTime endDate;

  private BigDecimal price;
}
