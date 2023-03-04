package me.fero.entities;

public record LeaderboardUser(String playerCardID, String titleId, boolean isBanned, boolean isAnonymized, String puuid,
                              String gameName, String tagline, int leaderboardRank, int rankedRating, int numberOfWins,
                              int competitiveTier) {
}
