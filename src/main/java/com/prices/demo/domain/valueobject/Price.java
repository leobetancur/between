package com.prices.demo.domain.valueobject;

import com.prices.demo.domain.exception.ExceptionInvalidData;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Price {

  @NonNull
  private final BigDecimal value;

  public Price(BigDecimal value){
    validatePrice(value);
    this.value = value;
  }

  private void validatePrice(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new ExceptionInvalidData("The price should be greater than zero");
    }
  }

}
