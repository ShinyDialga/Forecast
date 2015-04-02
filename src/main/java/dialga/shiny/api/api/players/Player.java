package dialga.shiny.api.api.players;

import dialga.shiny.api.api.teams.Team;
import dialga.shiny.api.utils.PlayerUtils;

import java.util.UUID;

/**
 * Created by ShinyDialga45 on 3/31/2015.
 */
public class Player {

    private String name;
    private UUID uuid;

    public Player(String name) {
        this.name = name;
    }

    public Player(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Team getTeam() {
        return PlayerUtils.getTeam(this);
    }

}
