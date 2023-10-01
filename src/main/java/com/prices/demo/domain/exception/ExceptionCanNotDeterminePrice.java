package com.prices.demo.domain.exception;

public class ExceptionCanNotDeterminePrice extends RuntimeException {

  public ExceptionCanNotDeterminePrice(String message){
    super(message);
  }
}
