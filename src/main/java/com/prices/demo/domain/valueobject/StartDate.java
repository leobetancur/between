package com.prices.demo.domain.valueobject;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class StartDate {
  @NonNull
  private final LocalDateTime value;
}
