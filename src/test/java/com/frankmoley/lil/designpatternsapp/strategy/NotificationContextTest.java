package com.frankmoley.lil.designpatternsapp.strategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.frankmoley.lil.designpatternsapp.DesignPatternsAppApplication;
import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import com.frankmoley.lil.designpatternsapp.strategy.impl.SmsNotificationStrategy;
import com.frankmoley.lil.designpatternsapp.strategy.impl.WhatsAppNotificationStrategy;
import java.util.Map;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = DesignPatternsAppApplication.class)
@RunWith(SpringRunner.class)
public class NotificationContextTest {

  @Autowired
  private NotificationContext notificationContext;

  @SpyBean
  private WhatsAppNotificationStrategy whatsAppNotificationStrategy;

  @SpyBean
  private SmsNotificationStrategy smsNotificationStrategy;

  @Test
  public void shouldContainAllNotificationStrategies() {
    Map<NotificationType, NotificationStrategy> sendNotificationByType = notificationContext.getSendNotificationByType();
    assertEquals(sendNotificationByType.size(), NotificationType.values().length);
  }

  @Test
  public void shouldInvokeWhatsAppNotificationStrategy() {
    String testMessage = "This is a test message.";
    notificationContext.sendMessage(testMessage, NotificationType.WHATSAPP);

    verify(whatsAppNotificationStrategy, times(1)).sendMessage(testMessage);
  }

  @Test
  public void shouldNotInvokeSmsNotificationStrategy() {
    String testMessage = "This is a test message.";
    notificationContext.sendMessage(testMessage, NotificationType.WHATSAPP);
    verify(smsNotificationStrategy, never()).sendMessage(testMessage);
  }
}