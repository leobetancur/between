package com.prices.demo.domain.entity;

import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.Currency;
import com.prices.demo.domain.valueobject.EndDate;
import com.prices.demo.domain.valueobject.Price;
import com.prices.demo.domain.valueobject.PriceList;
import com.prices.demo.domain.valueobject.Priority;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.domain.valueobject.StartDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemPrices {

  private final BrandId brandId;

  private final StartDate startDate;

  private final EndDate endDate;

  private final PriceList priceList;

  private final ProductId productId;

  private final Priority priority;

  private final Price price;

  private final Currency currency;

}
