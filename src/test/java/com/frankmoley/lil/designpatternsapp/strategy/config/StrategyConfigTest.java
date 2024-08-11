package com.frankmoley.lil.designpatternsapp.strategy.config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.frankmoley.lil.designpatternsapp.DesignPatternsAppApplication;
import com.frankmoley.lil.designpatternsapp.strategy.NotificationContext;
import com.frankmoley.lil.designpatternsapp.strategy.NotificationStrategy;
import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import com.frankmoley.lil.designpatternsapp.strategy.impl.SmsNotificationStrategy;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Understanding the StrategyConfig Class Before we dive into test cases, let's clarify the purpose
 * of the StrategyConfig class:
 * <p>
 * It's a Spring configuration class annotated with @Configuration. It uses @AllArgsConstructor for
 * dependency injection of notificationStrategies. It creates a Map of NotificationType to
 * NotificationStrategy for easy lookup. The sendNotificationByType method populates the Map and
 * returns it as a bean.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = DesignPatternsAppApplication.class)
public class StrategyConfigTest {

  @Autowired
  StrategyConfig strategyConfig;

  @Test
  public void testNotificationStrategiesList() {
    assertEquals(NotificationType.values().length,
        strategyConfig.getNotificationStrategies().size());
  }

  @Test
  public void testNotificationStrategiesListContainsSmsNotificationStrategy() {
    strategyConfig.getNotificationStrategies().stream()
        .anyMatch(strategy -> strategy instanceof SmsNotificationStrategy);
  }


  @Test
  public void testEmptyNotificationStrategies() {
    List<NotificationStrategy> emptyStrategies = Collections.emptyList();
    StrategyConfig config = new StrategyConfig(emptyStrategies);
    Map<NotificationType, NotificationStrategy> result = config.sendNotificationByType();
    assertEquals(0, result.size());
  }

  @Test
  public void testSingleNotificationStrategy() {
    NotificationStrategy strategy = mock(NotificationStrategy.class);
    when(strategy.notificationType()).thenReturn(NotificationType.EMAIL);
    List<NotificationStrategy> strategies = Lists.list(strategy);
    StrategyConfig config = new StrategyConfig(strategies);
    Map<NotificationType, NotificationStrategy> result = config.sendNotificationByType();
    assertEquals(1, result.size());
    assertEquals(strategy, result.get(NotificationType.EMAIL));
  }
}