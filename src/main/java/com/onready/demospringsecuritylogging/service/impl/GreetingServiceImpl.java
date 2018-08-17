package com.onready.demospringsecuritylogging.service.impl;

import com.onready.demospringsecuritylogging.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GreetingServiceImpl implements GreetingService {

  @Override
  public void sayHello() {
    log.info("I'm saying hello!!!!!");
  }

}
