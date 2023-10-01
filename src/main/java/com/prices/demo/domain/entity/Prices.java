package com.prices.demo.domain.entity;

import com.prices.demo.domain.input.PriceRequest;
import com.prices.demo.domain.output.PriceResponse;
import com.prices.demo.domain.exception.ExceptionCanNotDeterminePrice;
import com.prices.demo.domain.exception.ExceptionPriceNotFound;
import com.prices.demo.domain.port.PriceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Prices {

  public static final String PRICE_NOT_FOUND = "Price Not Found";
  public static final int ONE_RECORD = 1;
  public static final int INDEX_UNIQUE_RECORD = 0;
  public static final String IT_S_NOT_POSSIBLE_TO_DETERMINE_THE_PRICE = "It's not possible to determine the price";
  private final PriceRepository priceRepository;

  @Autowired
  public Prices(PriceRepository priceRepository){
    this.priceRepository = priceRepository;
  }

  public PriceResponse getItemPrice(PriceRequest priceRequest){
    List<ItemPrices> listItemPrices = priceRepository.getItemPrices(priceRequest);
    ItemPrices itemPrices = resolveUniqueItem(listItemPrices);
    return createResponse(itemPrices);
  }
  private ItemPrices resolveUniqueItem(List<ItemPrices> listItemPrices){
    if(listItemPrices.isEmpty()) {
      throw new ExceptionPriceNotFound(PRICE_NOT_FOUND);
    }
    if(listItemPrices.size() == ONE_RECORD){
      return listItemPrices.get(INDEX_UNIQUE_RECORD);
    }
    return resolvePriority(listItemPrices);
  }

  private ItemPrices resolvePriority(List<ItemPrices> listItemPrices) {
    return listItemPrices.stream()
        .max( (item1, item2) -> Integer.compare(item1.getPriority().getValue(), item2.getPriority().getValue()))
        .orElseThrow(()-> new ExceptionCanNotDeterminePrice(
            IT_S_NOT_POSSIBLE_TO_DETERMINE_THE_PRICE));
  }

  private PriceResponse createResponse(ItemPrices itemPrices) {
    return PriceResponse.builder()
        .productId(itemPrices.getProductId())
        .brandId(itemPrices.getBrandId())
        .priceList(itemPrices.getPriceList())
        .startDate(itemPrices.getStartDate())
        .endDate(itemPrices.getEndDate())
        .price(itemPrices.getPrice())
        .build();
  }

}
