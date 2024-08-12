package com.frankmoley.lil.designpatternsapp.strategy;

import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import com.frankmoley.lil.designpatternsapp.strategy.exception.NotFoundNotificationStrategy;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * A component responsible for managing and dispatching notifications based on their type.
 *
 * This class maintains a mapping of notification types to their corresponding notification strategies.
 * When a notification needs to be sent, it delegates the task to the appropriate strategy.
 */
@AllArgsConstructor
@Slf4j
@Getter
@Component
public class NotificationContext {

  /**
   * A map storing notification strategies indexed by their corresponding notification type.
   */
  private final Map<NotificationType, NotificationStrategy> sendNotificationByType;

  /**
   * Sends a message using the specified notification type.
   *
   * @param message          the message to be sent
   * @param notificationType the type of notification to send
   * @throws NotFoundNotificationStrategy if no notification strategy is found for the given type
   */
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
