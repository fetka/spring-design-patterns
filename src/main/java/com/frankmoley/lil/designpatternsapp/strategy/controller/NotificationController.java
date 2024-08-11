package com.frankmoley.lil.designpatternsapp.strategy.controller;

import com.frankmoley.lil.designpatternsapp.strategy.NotificationContext;
import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import com.frankmoley.lil.designpatternsapp.strategy.exception.NotFoundNotificationStrategy;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class NotificationController {

  private final NotificationContext notificationContext;

  @GetMapping(path = "/sendNotification")
  public String sendNotification(@RequestParam String message,
      @RequestParam String notificationType) {
    try {
      NotificationType notificationType1 = NotificationType.valueOf(notificationType);
      notificationContext.sendMessage(message, notificationType1);
      return message;
    }catch (Exception e){
      throw new NotFoundNotificationStrategy(notificationType);
    }
  }

  @GetMapping(path = "/do")
  public String doNothing() {
    return "do!";
  }

  @ExceptionHandler(NotFoundNotificationStrategy.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleNotFoundNotificationStrategy(NotFoundNotificationStrategy e) {
    return "Bad request!\nThis is not a valid strategy: " + e.getMessage();
  }
}