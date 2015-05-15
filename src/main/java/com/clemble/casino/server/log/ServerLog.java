package com.clemble.casino.server.log;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.log.ClientError;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

/**
 * Created by mavarazy on 5/15/15.
 */
public class ServerLog implements PlayerAware, CreatedAware {

    @Id
    final private String id;
    final private String player;
    final private ClientError error;
    final private DateTime created;

    @JsonCreator
    public ServerLog(
        @JsonProperty("id") String id,
        @JsonProperty("player") String player,
        @JsonProperty("error") ClientError error,
        @JsonProperty("created") DateTime created) {
        this.id = id;
        this.player = player;
        this.error = error;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public ClientError getError() {
        return error;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerLog)) return false;

        ServerLog serverLog = (ServerLog) o;

        if (!error.equals(serverLog.error)) return false;
        if (!player.equals(serverLog.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + error.hashCode();
        return result;
    }

}
