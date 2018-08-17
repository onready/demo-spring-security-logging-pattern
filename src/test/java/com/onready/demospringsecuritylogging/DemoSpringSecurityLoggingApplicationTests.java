package com.onready.demospringsecuritylogging;

import com.onready.demospringsecuritylogging.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringSecurityLoggingApplicationTests {

  @Autowired
  private GreetingService greetingService;

  @Test
  @WithMockUser(username = "John Doe")
  public void sayHello_withLoggedUsers_logsTheUsername() {
    greetingService.sayHello();
  }

}
