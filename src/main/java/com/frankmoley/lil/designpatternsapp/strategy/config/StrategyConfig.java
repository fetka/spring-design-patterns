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

@Configuration
@AllArgsConstructor
@Getter
public class StrategyConfig {

  private final List<NotificationStrategy> notificationStrategies;

  @Bean
  public Map<NotificationType, NotificationStrategy> sendNotificationByType() {
    Map<NotificationType, NotificationStrategy> messagesByType = new EnumMap<>(
        NotificationType.class);

    notificationStrategies.forEach(notificationStrategy ->
        messagesByType.put(notificationStrategy.notificationType(), notificationStrategy));

    return messagesByType;
  }
}