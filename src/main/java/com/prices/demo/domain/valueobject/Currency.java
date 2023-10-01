package com.prices.demo.domain.valueobject;

import com.prices.demo.domain.exception.ExceptionInvalidData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NonNull;


public class Currency {

  private static final String REGEX_TYPE_CURRENCY = "[a-zA-Z]{3}";

  private final Pattern pattern = Pattern.compile(REGEX_TYPE_CURRENCY);

  @NonNull
  @Getter
  private final String value;

  public Currency(String value){
    validateTypeCurrency(value);
    this.value = value;
  }

  private void validateTypeCurrency(String value) {
    Matcher matcher = pattern.matcher(value);
    if (!matcher.matches()) {
      throw new ExceptionInvalidData("Type Currency Invalid: "+value);
    }
  }


}
