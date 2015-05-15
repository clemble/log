package com.clemble.casino.server.log.spring;

import com.clemble.casino.server.log.controller.ClembleLogController;
import com.clemble.casino.server.log.repository.ServerLogRepository;
import com.clemble.casino.server.player.notification.SystemNotificationService;
import com.clemble.casino.server.spring.common.CommonSpringConfiguration;
import com.clemble.casino.server.spring.common.MongoSpringConfiguration;
import com.clemble.casino.server.spring.common.SpringConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

/**
 * Created by mavarazy on 5/15/15.
 */
@Configuration
@Import({CommonSpringConfiguration.class, MongoSpringConfiguration.class})
public class LogSpringConfiguration implements SpringConfiguration {

    @Bean
    public ServerLogRepository playerProfileRepository(MongoRepositoryFactory mongoRepositoryFactory) {
        return mongoRepositoryFactory.getRepository(ServerLogRepository.class);
    }

    @Bean
    public ClembleLogController logController(SystemNotificationService notificationService, ServerLogRepository logRepository) {
        return new ClembleLogController(logRepository, notificationService);
    }

}
