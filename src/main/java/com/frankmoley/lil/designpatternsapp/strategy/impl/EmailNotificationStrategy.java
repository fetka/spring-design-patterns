package com.frankmoley.lil.designpatternsapp.strategy.impl;

import com.frankmoley.lil.designpatternsapp.strategy.NotificationStrategy;
import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationStrategy extends NotificationStrategy {

  @Override
  public void sendMessage(String message) {
    log.info("message send to email" + message);
  }

  @Override
  public NotificationType notificationType() {
    return NotificationType.EMAIL;
  }
}
