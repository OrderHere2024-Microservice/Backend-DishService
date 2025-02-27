package com.orderhere.dish.DishService.exception;

public class DataIntegrityViolationException extends RuntimeException {

  public DataIntegrityViolationException(String message) {
    super(message);
  }
}
