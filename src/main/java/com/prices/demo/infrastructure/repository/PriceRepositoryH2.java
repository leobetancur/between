package com.prices.demo.infrastructure.repository;

import com.prices.demo.domain.entity.ItemPrices;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.port.PriceRepository;
import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.Currency;
import com.prices.demo.domain.valueobject.EndDate;
import com.prices.demo.domain.valueobject.Price;
import com.prices.demo.domain.valueobject.PriceList;
import com.prices.demo.domain.valueobject.Priority;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.domain.valueobject.StartDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryH2 implements PriceRepository {

  private PriceRepositoryJpa priceRepositoryJpa;

  @Autowired
  public PriceRepositoryH2(PriceRepositoryJpa priceRepositoryJpa) {
    this.priceRepositoryJpa = priceRepositoryJpa;
  }

  @Override
  public List<ItemPrices> getItemPrices(PriceRequest priceRequest) {
    List<PriceDataBaseEntity> priceDataBaseEntityList =  priceRepositoryJpa.findPrices(
        priceRequest.getProductId().getValue(),
        priceRequest.getBrandId().getValue(),
        priceRequest.getApplicationDate().getValue());
    return mapPriceDataBaseEntityToItemPrice
        (priceDataBaseEntityList);
  }

  private List<ItemPrices> mapPriceDataBaseEntityToItemPrice(List<PriceDataBaseEntity> priceDataBaseEntityList) {
    return priceDataBaseEntityList.stream()
        .map(priceDataBaseEntity -> ItemPrices.builder()
            .priceList(new PriceList(priceDataBaseEntity.getPriceList()))
            .productId(new ProductId(priceDataBaseEntity.getProductId()))
            .price(new Price(priceDataBaseEntity.getPrice()))
            .endDate(new EndDate(priceDataBaseEntity.getEndDate()))
            .startDate(new StartDate(priceDataBaseEntity.getStartDate()))
            .priority(new Priority(priceDataBaseEntity.getPriority()))
            .brandId(new BrandId(priceDataBaseEntity.getBrandId()))
            .currency(new Currency(priceDataBaseEntity.getCurrency()))
            .build()
        ).collect(Collectors.toList());
  }



}
