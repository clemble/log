package com.clemble.casino.server.log.controller;

import com.clemble.casino.log.ClientError;
import com.clemble.casino.log.LogWebMapping;
import com.clemble.casino.log.service.ClembleLogService;
import com.clemble.casino.server.event.log.SystemClientErrorEvent;
import com.clemble.casino.server.log.ServerLog;
import com.clemble.casino.server.log.repository.ServerLogRepository;
import com.clemble.casino.server.player.notification.SystemNotificationService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mavarazy on 5/15/15.
 */
@RestController
public class ClembleLogController implements ClembleLogService {

    final private ServerLogRepository logRepository;
    final private SystemNotificationService notificationService;

    public ClembleLogController(ServerLogRepository logRepository, SystemNotificationService notificationService) {
        this.logRepository = logRepository;
        this.notificationService = notificationService;
    }

    @Override
    public ClientError error(ClientError error) {
        throw new UnsupportedOperationException();
    }

    @RequestMapping(method = RequestMethod.POST, value = LogWebMapping.LOG_ERROR)
    @ResponseStatus(HttpStatus.CREATED)
    public ClientError httpError(
        @CookieValue(value = "player", required = false) String player,
        @RequestBody ClientError error) {
        // Step 1. Generating log message
        ServerLog log = new ServerLog(null, player, error, DateTime.now(DateTimeZone.UTC));
        // Step 2. Saving new log message
        ServerLog savedErrorLog = logRepository.save(log);
        // Step 3. Returning saved error
        if (player != null) {
            notificationService.send(new SystemClientErrorEvent(player, error));
        }
        return  savedErrorLog.getError();
    }

}
