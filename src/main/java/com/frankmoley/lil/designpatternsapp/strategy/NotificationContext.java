package com.frankmoley.lil.designpatternsapp.strategy;

import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import com.frankmoley.lil.designpatternsapp.strategy.exception.NotFoundNotificationStrategy;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
@Getter
public class NotificationContext {

  private final Map<NotificationType, NotificationStrategy> sendNotificationByType;

  public void sendMessage(String message, NotificationType notificationType)
      throws NotFoundNotificationStrategy {
    NotificationStrategy notificationStrategy = sendNotificationByType.getOrDefault(
        notificationType, null);
    if (Objects.isNull(notificationStrategy)) {
      throw new NotFoundNotificationStrategy(
          "Notification Type not found. type: " + notificationType);
    }
    notificationStrategy.sendMessage(message);
  }
}
