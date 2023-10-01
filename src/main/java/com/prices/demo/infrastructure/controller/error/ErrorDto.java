package com.prices.demo.infrastructure.controller.error;

import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class ErrorDto {

  private final String error;
  private final LocalDateTime timestamp;

  public ErrorDto(RuntimeException ex){
    this.error = ex.getMessage();
    this.timestamp = LocalDateTime.now();
  }
}
