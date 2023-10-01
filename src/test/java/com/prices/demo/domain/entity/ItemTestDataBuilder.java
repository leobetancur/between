package com.prices.demo.domain.entity;

import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.Currency;
import com.prices.demo.domain.valueobject.EndDate;
import com.prices.demo.domain.valueobject.Price;
import com.prices.demo.domain.valueobject.PriceList;
import com.prices.demo.domain.valueobject.Priority;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.domain.valueobject.StartDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemTestDataBuilder {
  private BrandId brandId;
  private StartDate startDate;
  private EndDate endDate;
  private PriceList priceList;
  private ProductId productId;
  private Priority priority;
  private Price price;
  private Currency currency;

  public ItemTestDataBuilder(){
    brandId = new BrandId(1);
    startDate = new StartDate(LocalDateTime.now());
    endDate = new EndDate(LocalDateTime.now());
    priceList = new PriceList(1);
    productId = new ProductId("35455");
    priority = new Priority(1);
    price = new Price(BigDecimal.TEN);
    currency = new Currency("EUR");
  }

  public ItemTestDataBuilder withBrandId(BrandId brandId) {
    this.brandId = brandId;
    return this;
  }

  public ItemTestDataBuilder withStartDate(StartDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public ItemTestDataBuilder withEndDate(EndDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public ItemTestDataBuilder withPriceList(PriceList priceList) {
    this.priceList = priceList;
    return this;
  }

  public ItemTestDataBuilder withProductId(ProductId productId) {
    this.productId = productId;
    return this;
  }

  public ItemTestDataBuilder withPriority(Priority priority) {
    this.priority = priority;
    return this;
  }

  public ItemTestDataBuilder withPrice(Price price) {
    this.price = price;
    return this;
  }

  public ItemTestDataBuilder withCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  public ItemPrices build(){
    return ItemPrices.builder()
        .brandId(brandId)
        .startDate(startDate)
        .endDate(endDate)
        .priceList(priceList)
        .productId(productId)
        .priority(priority)
        .price(price)
        .currency(currency)
        .build();
  }


}
