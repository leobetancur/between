package com.prices.demo.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class ProductId {
  @NonNull
  private final String value;
}
