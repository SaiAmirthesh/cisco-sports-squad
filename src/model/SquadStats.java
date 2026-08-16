package model;

import java.util.ArrayList;
import java.util.List;

public class SquadStats {
    private int totalPlayers;
    private int goalkeepers;
    private int defenders;
    private int forwards;
    private int utility;
    private int year2Count;
    private int year3Count;
    private final List<String> unavailablePlayers = new ArrayList<>();

    public void incrementTotalPlayers() {
        totalPlayers++;
    }

    public void incrementPosition(Position position) {
        switch (position) {
            case GOALKEEPER -> goalkeepers++;
            case DEFENDER -> defenders++;
            case FORWARD -> forwards++;
            case UTILITY -> utility++;
        }
    }

    public void incrementCohort(Cohort cohort) {
        switch (cohort) {
            case YEAR_2 -> year2Count++;
            case YEAR_3 -> year3Count++;
        }
    }

    public void addUnavailablePlayer(String playerId) {
        unavailablePlayers.add(playerId);
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public int getGoalkeepers() {
        return goalkeepers;
    }

    public int getDefenders() {
        return defenders;
    }

    public int getForwards() {
        return forwards;
    }

    public int getUtility() {
        return utility;
    }

    public int getYear2Count() {
        return year2Count;
    }

    public int getYear3Count() {
        return year3Count;
    }

    public List<String> getUnavailablePlayers() {
        return List.copyOf(unavailablePlayers);
    }
}
