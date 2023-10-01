package com.prices.demo.application;

import com.prices.demo.domain.entity.Prices;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.output.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricesService {

  private Prices prices;

  @Autowired
  public PricesService(Prices prices){
    this.prices = prices;
  }

  public PriceResponse getPrice(PriceRequest priceRequest) {
    return prices.getItemPrice(priceRequest);
  }

}
