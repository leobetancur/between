package com.prices.demo.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prices.demo.application.PricesService;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.output.PriceResponse;
import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.EndDate;
import com.prices.demo.domain.valueobject.Price;
import com.prices.demo.domain.valueobject.PriceList;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.domain.valueobject.StartDate;
import com.prices.demo.infrastructure.controller.output.PriceDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

  private PricesController pricesController;

  @Mock
  private PricesService pricesService;

  @BeforeEach
  void setup(){
    pricesController = new PricesController(pricesService);
  }

  @Test
  @DisplayName("Shuold recive the request and generate Response Entity with the price data")
  void shouldGetPrice(){

    PriceResponse priceResponse = PriceResponse.builder()
        .price(new Price(BigDecimal.valueOf(4,43)))
        .priceList(new PriceList(1))
        .startDate(new StartDate(LocalDateTime.of(2023,4,2,12,3,4)))
        .endDate(new EndDate(LocalDateTime.of(2023,6,2,12,3,4)))
        .brandId(new BrandId(1))
        .productId(new ProductId("8349"))
        .build();
    when(pricesService.getPrice(any(PriceRequest.class))).thenReturn(priceResponse);

    ResponseEntity<PriceDto> reponseEntity = pricesController
        .getPrice(LocalDateTime.of(2023,5,2,12,3,4), "8349", 1);

    assertEquals(HttpStatus.OK, reponseEntity.getStatusCode());
    assertEquals(BigDecimal.valueOf(4,43), reponseEntity.getBody().getPrice());
    assertEquals(1, reponseEntity.getBody().getPriceList());
    assertEquals(LocalDateTime.of(2023,4,2,12,3,4), reponseEntity.getBody().getStartDate());
    assertEquals(LocalDateTime.of(2023,6,2,12,3,4), reponseEntity.getBody().getEndDate());
    assertEquals(1, reponseEntity.getBody().getBrandId());
    assertEquals("8349", reponseEntity.getBody().getProductId());

  }
}
