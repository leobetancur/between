package com.prices.demo.domain.exception;

public class ExceptionPriceNotFound extends RuntimeException{
  public ExceptionPriceNotFound(String message){
    super(message);
  }
}
