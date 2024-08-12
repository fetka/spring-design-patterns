package com.frankmoley.lil.designpatternsapp.strategy.config;

import com.frankmoley.lil.designpatternsapp.strategy.NotificationStrategy;
import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up notification strategies.
 *
 * This class defines a bean that creates a map of notification types to their corresponding
 * notification strategies, based on a list of available strategies.
 */
@Getter
@AllArgsConstructor
@Configuration
public class StrategyConfig {

  /**
   * A list of available notification strategies.
   */
  private final List<NotificationStrategy> notificationStrategies;

  /**
   * Creates a map of notification types to their corresponding notification strategies.
   *
   * @return a map of notification types to notification strategies
   */
  @Bean
  public Map<NotificationType, NotificationStrategy> sendNotificationByType() {
    Map<NotificationType, NotificationStrategy> messagesByType = new EnumMap<>(
        NotificationType.class);

    notificationStrategies.forEach(notificationStrategy ->
        messagesByType.put(notificationStrategy.notificationType(), notificationStrategy));

    return messagesByType;
  }
}