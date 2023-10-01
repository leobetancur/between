package com.prices.demo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prices.demo.domain.entity.Prices;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.output.PriceResponse;
import com.prices.demo.domain.valueobject.Price;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  private PricesService priceService;

  @Mock
  private Prices prices;
  @BeforeEach
  public void setup(){
    priceService = new PricesService(prices);
  }

  @Test
  @DisplayName("Should call to entity Prices and getting the response with price")
  void callGetPrice(){
    when(prices.getItemPrice(any(PriceRequest.class))).thenReturn(PriceResponse.builder()
        .price(new Price(BigDecimal.valueOf(35,4)))
        .build());

   PriceResponse priceResponse = priceService.getPrice(PriceRequest.builder().build());

   assertEquals(BigDecimal.valueOf(35,4), priceResponse.getPrice().getValue());
  }
}
