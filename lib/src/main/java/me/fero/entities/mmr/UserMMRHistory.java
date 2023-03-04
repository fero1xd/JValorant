package me.fero.entities.mmr;

public record UserMMRHistory(int currentTier, String currentTierPatched, Images images,
        int rankingInTier, int mmrChangeToLastGame, int elo, String date, String dateRaw) {
}
