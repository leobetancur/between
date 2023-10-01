package com.prices.demo.domain.valueobject;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class ApplicationDate {
  @NonNull
  private final LocalDateTime value;

}
