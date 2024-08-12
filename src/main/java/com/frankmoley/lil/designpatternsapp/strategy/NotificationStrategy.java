package com.frankmoley.lil.designpatternsapp.strategy;

import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;

public abstract class NotificationStrategy {

  public void sendMessage(String message){}

  public NotificationType notificationType(){
    return null;
  }


}
