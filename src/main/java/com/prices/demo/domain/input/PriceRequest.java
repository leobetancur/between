package com.prices.demo.domain.input;

import com.prices.demo.domain.valueobject.ApplicationDate;
import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.ProductId;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PriceRequest {
  private final ApplicationDate applicationDate;
  private final ProductId productId;
  private final BrandId brandId;


}
