package com.prices.demo.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.prices.demo.domain.entity.ItemPrices;
import com.prices.demo.domain.entity.ItemTestDataBuilder;
import com.prices.demo.domain.entity.Prices;
import com.prices.demo.domain.exception.ExceptionPriceNotFound;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.output.PriceResponse;
import com.prices.demo.domain.port.PriceRepository;
import com.prices.demo.domain.valueobject.BrandId;
import com.prices.demo.domain.valueobject.EndDate;
import com.prices.demo.domain.valueobject.Price;
import com.prices.demo.domain.valueobject.PriceList;
import com.prices.demo.domain.valueobject.Priority;
import com.prices.demo.domain.valueobject.ProductId;
import com.prices.demo.domain.valueobject.StartDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PricesTest {

  private Prices prices;

  @Mock
  private PriceRepository priceRepository;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

  @BeforeEach
  private void setup(){
    prices = new Prices(priceRepository);
  }

  @Test
  @DisplayName("When it found one item, it should be returned")
  void oneItemFound(){

    PriceRequest priceRequest = PriceRequest.builder().build();
    ItemPrices itemPrices = new ItemTestDataBuilder()
        .withProductId(new ProductId("35455"))
        .withBrandId(new BrandId(1))
        .withPriceList(new PriceList(1))
        .withStartDate(new StartDate(LocalDateTime.parse("2020-06-14-00.00.00", formatter)))
        .withEndDate(new EndDate(LocalDateTime.parse("2020-12-31-23.59.59", formatter)))
        .withPrice(new Price(BigDecimal.valueOf(35.50)))
        .build();
    List<ItemPrices> itemPricesList = Arrays.asList(itemPrices);
    when(priceRepository.getItemPrices(priceRequest)).thenReturn(itemPricesList);

    PriceResponse priceResponse = prices.getItemPrice(priceRequest);

    assertEquals( "35455", priceResponse.getProductId().getValue());
    assertEquals( 1 , priceResponse.getBrandId().getValue());
    assertEquals(1, priceResponse.getPriceList().getValue());
    assertEquals(LocalDateTime.of(2020, 06,14,0,0,0), priceResponse.getStartDate().getValue());
    assertEquals(LocalDateTime.of(2020, 12,31,23,59,59), priceResponse.getEndDate().getValue());
    assertEquals(BigDecimal.valueOf(35.50), priceResponse.getPrice().getValue());
  }

  @Test
  @DisplayName("When it found more than one item, It should return the one with the highest priority")
  void moreThanOneItemFound(){
    PriceRequest priceRequest = PriceRequest.builder().build();
    ItemPrices itemPrices1 = new ItemTestDataBuilder()
        .withStartDate(new StartDate(LocalDateTime.parse("2020-06-14-00.00.00", formatter)))
        .withEndDate(new EndDate(LocalDateTime.parse("2020-12-31-23.59.59", formatter)))
        .withPrice(new Price(BigDecimal.valueOf(25.50)))
        .withPriority(new Priority(0))
        .build();
    ItemPrices itemPrices2 = new ItemTestDataBuilder()
        .withStartDate(new StartDate(LocalDateTime.parse("2020-06-14-15.00.00", formatter)))
        .withEndDate(new EndDate(LocalDateTime.parse("2020-06-14-18.30.00", formatter)))
        .withPrice(new Price(BigDecimal.valueOf(25.45)))
        .withPriority(new Priority(1))
        .build();
    List<ItemPrices> itemPricesList = Arrays.asList(itemPrices1, itemPrices2);
    when(priceRepository.getItemPrices(priceRequest)).thenReturn(itemPricesList);

    PriceResponse priceResponse = prices.getItemPrice(priceRequest);

    assertEquals(BigDecimal.valueOf(25.45), priceResponse.getPrice().getValue());
  }

  @Test
  @DisplayName("When don't find any product, it shuold return error")
  void dontFoundProduct(){
    PriceRequest priceRequest = PriceRequest.builder().build();
    List<ItemPrices> itemPricesList = new ArrayList<>();
    when(priceRepository.getItemPrices(priceRequest)).thenReturn(itemPricesList);

    assertThrows(ExceptionPriceNotFound.class, ()-> prices.getItemPrice(priceRequest));
  }
}
