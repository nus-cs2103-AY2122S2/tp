package seedu.address.model;


import java.util.HashMap;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

public class UniqueTeamList {
    private final HashMap<TeamName, Team> teamNameToTeamMap;
    
    public UniqueTeamList() {
        this.teamNameToTeamMap = new HashMap<TeamName, Team>();
    }

    public boolean containsTeam(TeamName teamName) {
        return this.teamNameToTeamMap.containsKey(teamName);
    }

    public boolean containsTeam(Team team) {
        return containsTeam(team.getTeamName());
    }

    public void addTeam(Team team) {
        TeamName teamName = team.getTeamName();
        if (!this.teamNameToTeamMap.containsKey(teamName)) {
            this.teamNameToTeamMap.put(teamName, team);
        }
    }

    public void deleteTeam(Team team) {
        TeamName teamName = team.getTeamName();
        if (this.teamNameToTeamMap.containsKey(teamName)) {
            this.teamNameToTeamMap.remove(teamName);
        }
    }
    
}
