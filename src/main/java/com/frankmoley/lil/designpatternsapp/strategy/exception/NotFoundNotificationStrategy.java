package com.frankmoley.lil.designpatternsapp.strategy.exception;

public class NotFoundNotificationStrategy extends RuntimeException {
  public NotFoundNotificationStrategy(String message) {
    super(message);
  }
}