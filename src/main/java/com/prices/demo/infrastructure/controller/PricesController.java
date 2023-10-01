package com.prices.demo.infrastructure.controller;

import com.prices.demo.application.PricesService;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.output.PriceResponse;
import com.prices.demo.domain.valueobject.ApplicationDate;
import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.infrastructure.controller.output.PriceDto;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PricesController {

  private PricesService pricesService;

  @Autowired
  public PricesController(PricesService pricesService) {
    this.pricesService = pricesService;
  }

  @GetMapping
  public ResponseEntity<PriceDto> getPrice(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime applicationDate,
      @RequestParam String productId,
      @RequestParam int brandId){

    PriceRequest priceRequest = PriceRequest.builder()
        .applicationDate(new ApplicationDate(applicationDate))
        .productId(new ProductId(productId))
        .brandId(new BrandId(brandId))
        .build();

    PriceResponse priceResponse= pricesService.getPrice(priceRequest);

    return ResponseEntity.ok(convertInPriceDto(priceResponse));
  }

  private PriceDto convertInPriceDto(PriceResponse priceResponse) {
    return PriceDto.builder()
        .price(priceResponse.getPrice().getValue())
        .priceList(priceResponse.getPriceList().getValue())
        .startDate(priceResponse.getStartDate().getValue())
        .endDate(priceResponse.getEndDate().getValue())
        .brandId(priceResponse.getBrandId().getValue())
        .productId(priceResponse.getProductId().getValue())
        .build();
  }
}
