package dialga.shiny.api.api.teams;

import dialga.shiny.api.utils.TeamUtils;

import java.util.List;

/**
 * Created by ShinyDialga45 on 3/31/2015.
 */
public class Team {

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getMembers() {
        return TeamUtils.getMembers(name);
    }

}
