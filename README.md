# Demo Spring Security Logging

This is a sample project that uses a custom Logback Pattern Layout to include the name of the logged user in the logging pattern.
In this example, we are using the spring-boot-starter-security.


### Main concepts


First, create this 2 classes to create a custom PatternLayout:


```java
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
```
```java
package com.onready.demospringsecuritylogging.config.logging;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithUser extends PatternLayout {

  static {
    PatternLayout.defaultConverterMap.put("user", UserConverter.class.getName());
  }

}

```

Then, create a custom logback-spring.xml with the following configuration:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration>

  <springProperty scope="context" name="APP_LOG_LEVEL" source="logging.level.com.onready.demospringsecuritylogging"/>
  <springProperty scope="context" name="SPRING_LOG_LEVEL" source="logging.level.org.springframework"/>
  <springProperty scope="context" name="CONSOLE_LOG_PATTERN" source="logging.pattern.console"/>

  <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder name="enc" class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
      <layout class="com.onready.demospringsecuritylogging.config.logging.PatternLayoutWithUser">
        <pattern>${CONSOLE_LOG_PATTERN}</pattern>
      </layout>
    </encoder>
  </appender>

  <logger name="com.onready.demospringsecuritylogging" level="${APP_LOG_LEVEL}" additivity="false">
    <appender-ref ref="CONSOLE"/>
  </logger>

  <logger name="org.springframework.web" level="${SPRING_LOG_LEVEL}" additivity="false">
    <appender-ref ref="CONSOLE"/>
  </logger>

  <root level="INFO" additivity="false">
    <appender-ref ref="CONSOLE"/>
  </root>

</configuration>
```

Finally, at the application.properties:


```properties
logging.level.com.onready.demospringsecuritylogging=info
logging.level.org.springframework=WARN
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{35} [%user] [%M:%line] - %msg %n
```

The result:

```
2018-08-16 23:30:05.061 [main] INFO  c.o.d.s.impl.GreetingServiceImpl [John Doe] [sayHello:13] - I'm saying hello!!!!! 
```

If you want to add more packages, you will need to add a new property and a new logger.

This project is inspired in this [post](https://www.codelord.net/2010/08/27/logging-with-a-context-users-in-logback-and-spring-security/)

