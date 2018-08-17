package com.onready.demospringsecuritylogging.config.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserConverter extends ClassicConverter {

  @Override
  public String convert(ILoggingEvent iLoggingEvent) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return "NO_USER";
    } else {
      return authentication.getName();
    }
  }

}
