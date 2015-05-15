package com.clemble.casino.server.log.repository;

import com.clemble.casino.server.log.ServerLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mavarazy on 5/15/15.
 */
public interface ServerLogRepository extends MongoRepository<ServerLog, String> {
}
