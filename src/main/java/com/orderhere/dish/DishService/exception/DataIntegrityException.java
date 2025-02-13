package com.orderhere.dish.DishService.exception;

public class DataIntegrityException extends RuntimeException {
  public DataIntegrityException(String message) {
    super(message);
  }
}
