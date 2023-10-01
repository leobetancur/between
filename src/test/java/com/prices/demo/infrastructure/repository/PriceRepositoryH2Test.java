package com.prices.demo.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.prices.demo.domain.entity.ItemPrices;
import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.valueobject.ApplicationDate;
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
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryH2Test {

  private PriceRepositoryH2 priceRepositoryH2;

  @Mock
  private PriceRepositoryJpa priceRepositoryJpa;

  @BeforeEach
  void setup(){
    priceRepositoryH2 = new PriceRepositoryH2(priceRepositoryJpa);
  }

  @Test
  @DisplayName("It Should get prices from h2 repository")
  void getDataFromH2Repository(){
    PriceDataBaseEntity priceDataBaseEntity = new PriceDataBaseEntity();
    priceDataBaseEntity.setPriceList(1);
    priceDataBaseEntity.setProductId("1234");
    priceDataBaseEntity.setPrice(BigDecimal.valueOf(34,56));
    priceDataBaseEntity.setStartDate(LocalDateTime.of(2023,1,21,1,1,1));
    priceDataBaseEntity.setEndDate(LocalDateTime.of(2023,2,21,1,1,1));
    priceDataBaseEntity.setPriority(1);
    priceDataBaseEntity.setBrandId(1);
    priceDataBaseEntity.setCurrency("EUR");
    List<PriceDataBaseEntity> priceDataBaseEntityList = Arrays.asList(priceDataBaseEntity);
    when(priceRepositoryJpa.findPrices(anyString(), anyInt(), any(LocalDateTime.class)))
        .thenReturn(priceDataBaseEntityList);

    List<ItemPrices> itemPricesList = priceRepositoryH2.getItemPrices(PriceRequest.builder()
            .brandId(new BrandId(1))
            .productId(new ProductId("1234"))
            .applicationDate(new ApplicationDate(LocalDateTime.of(2023,1,21,1,1,1)))
        .build());

    assertEquals(1, itemPricesList.get(0).getPriceList().getValue());
    assertEquals("1234", itemPricesList.get(0).getProductId().getValue());
    assertEquals(BigDecimal.valueOf(34,56), itemPricesList.get(0).getPrice().getValue());
    assertEquals(LocalDateTime.of(2023,1,21,1,1,1),
        itemPricesList.get(0).getStartDate().getValue());
    assertEquals(LocalDateTime.of(2023,2,21,1,1,1),
        itemPricesList.get(0).getEndDate().getValue());
    assertEquals(1, itemPricesList.get(0).getPriority().getValue());
    assertEquals("EUR", itemPricesList.get(0).getCurrency().getValue());


  }

}
