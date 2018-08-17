package com.onready.demospringsecuritylogging.config.logging;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithUser extends PatternLayout {

  static {
    PatternLayout.defaultConverterMap.put("user", UserConverter.class.getName());
  }

}
