package com.frankmoley.lil.designpatternsapp.strategy;

import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;

public interface NotificationStrategy {

  void sendMessage(String message);

  NotificationType notificationType();

}
