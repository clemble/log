package com.clemble.casino.server.log.spring;

import com.clemble.casino.server.spring.WebBootSpringConfiguration;
import com.clemble.casino.server.spring.common.ClembleBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by mavarazy on 5/15/15.
 */
@Configuration
@Import({ WebBootSpringConfiguration.class, LogSpringConfiguration.class })
public class LogApplication implements ClembleBootApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LogApplication.class, args);
    }

}
