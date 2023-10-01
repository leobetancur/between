package com.prices.demo.domain.output;


import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.EndDate;
import com.prices.demo.domain.valueobject.Price;
import com.prices.demo.domain.valueobject.PriceList;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.domain.valueobject.StartDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PriceResponse {
  private final ProductId productId;
  private final BrandId brandId;
  private final PriceList priceList;
  private final StartDate startDate;
  private final EndDate endDate;
  private final Price price;
}
