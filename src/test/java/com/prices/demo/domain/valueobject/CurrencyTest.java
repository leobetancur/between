package com.prices.demo.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.prices.demo.domain.exception.ExceptionInvalidData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CurrencyTest {

  @Test
  @DisplayName("Currency with value with 3 char, it should be create")
  void correctValue(){
    Currency currency = new Currency("EUR");
    assertEquals("EUR", currency.getValue());
  }

  @Test
  @DisplayName("Currency with value diferent to 3 char, it should return error")
  void wrongValue(){
    assertThrows(ExceptionInvalidData.class, ()-> new Currency("EUROS"));
  }
}
