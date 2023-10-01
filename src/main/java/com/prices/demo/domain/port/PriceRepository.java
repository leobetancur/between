package com.prices.demo.domain.port;

import com.prices.demo.domain.entity.ItemPrices;
import com.prices.demo.domain.input.PriceRequest;
import java.util.List;

public interface PriceRepository {
  List<ItemPrices> getItemPrices(PriceRequest priceRequest);
}
