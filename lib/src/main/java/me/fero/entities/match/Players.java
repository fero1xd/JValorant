package me.fero.entities.match;

public class Players {
    private final MatchPlayer[] allPlayers;

    private final MatchPlayer[] redTeamPlayers;
    private final MatchPlayer[] blueTeamPlayers;

    public Players(MatchPlayer[] red, MatchPlayer[] blue, MatchPlayer[] allPlayers) {
        this.redTeamPlayers = red;
        this.blueTeamPlayers = blue;
        this.allPlayers = allPlayers;
    }

    public Players(MatchPlayer[] red, MatchPlayer[] blue) {
        this.redTeamPlayers = red;
        this.blueTeamPlayers = blue;

        this.allPlayers = new MatchPlayer[red.length + blue.length];

        System.arraycopy(red, 0, this.allPlayers, 0, red.length);
        System.arraycopy(blue, 0, this.allPlayers, red.length, blue.length);
    }


    public MatchPlayer[] getAllPlayers() {
        return allPlayers;
    }

    public MatchPlayer[] getBlueTeamPlayers() {
        return blueTeamPlayers;
    }

    public MatchPlayer[] getRedTeamPlayers() {
        return redTeamPlayers;
    }
}
