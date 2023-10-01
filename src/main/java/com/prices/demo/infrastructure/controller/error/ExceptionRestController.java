package com.prices.demo.infrastructure.controller.error;

import com.prices.demo.domain.exception.ExceptionCanNotDeterminePrice;
import com.prices.demo.domain.exception.ExceptionInvalidData;
import com.prices.demo.domain.exception.ExceptionPriceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionRestController {
  @ExceptionHandler(value = { ExceptionPriceNotFound.class })
  protected ResponseEntity<Object> handleConflict(ExceptionPriceNotFound ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(ex));
  }

  @ExceptionHandler(value = { ExceptionInvalidData.class, ExceptionCanNotDeterminePrice.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(ex));
  }
}


