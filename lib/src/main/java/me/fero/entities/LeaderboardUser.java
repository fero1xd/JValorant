package me.fero.entities;

public record LeaderboardUser(String playerCardID, String titleId, boolean isBanned, boolean isAnonymized, String puuid,
                              String gameName, String tagline, int leaderboardRank, int rankedRating, int numberOfWins,
                              int competitiveTier) {
    @Override
    public String playerCardID() {
        return playerCardID;
    }

    @Override
    public String titleId() {
        return titleId;
    }

    @Override
    public boolean isBanned() {
        return isBanned;
    }

    @Override
    public boolean isAnonymized() {
        return isAnonymized;
    }

    @Override
    public String puuid() {
        return puuid;
    }

    @Override
    public String gameName() {
        return gameName;
    }

    @Override
    public String tagline() {
        return tagline;
    }

    @Override
    public int leaderboardRank() {
        return leaderboardRank;
    }

    @Override
    public int rankedRating() {
        return rankedRating;
    }

    @Override
    public int numberOfWins() {
        return numberOfWins;
    }

    @Override
    public int competitiveTier() {
        return competitiveTier;
    }
}
