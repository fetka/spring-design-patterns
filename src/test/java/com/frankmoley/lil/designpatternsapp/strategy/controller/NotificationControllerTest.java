package com.frankmoley.lil.designpatternsapp.strategy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.frankmoley.lil.designpatternsapp.DesignPatternsAppApplication;
import com.frankmoley.lil.designpatternsapp.strategy.enums.NotificationType;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = DesignPatternsAppApplication.class)
@AutoConfigureMockMvc
public class NotificationControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testTypeSMS() throws Exception {
    String message = "Hello! " + NotificationType.SMS.name();
    mvc.perform(get("/sendNotification")
            .contentType(MediaType.ALL)
            .param("message", message)
            .param("notificationType", NotificationType.SMS.name())
        )
        .andExpect(status().isOk())
        .andExpect(content().string(message));
  }

  @Test
  public void testTypeEmail() throws Exception {
    String message = "Hello! " + NotificationType.EMAIL.name();
    mvc.perform(get("/sendNotification")
            .contentType(MediaType.ALL)
            .param("message", message)
            .param("notificationType", NotificationType.EMAIL.name())
        )
        .andExpect(status().isOk())
        .andExpect(content().string(message));
  }

  @Test
  public void testTypeUnknown() throws Exception {
    String unknownType = "UNKNOWN";
    String message = "Hello! " + unknownType;
    mvc.perform(get("/sendNotification")
            .contentType(MediaType.ALL)
            .param("message", message)
            .param("notificationType", unknownType)
        )
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("Bad request!\nThis is not a valid strategy: " + unknownType));
  }


}