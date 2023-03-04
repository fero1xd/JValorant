package me.fero.entities.mmr;

public record UserMMRHistory(int currentTier, String currentTierPatched, Images images,
        int rankingInTier, int mmrChangeToLastGame, int elo, String date, String dateRaw) {

    @Override
    public int currentTier() {
        return currentTier;
    }

    @Override
    public String currentTierPatched() {
        return currentTierPatched;
    }

    @Override
    public Images images() {
        return images;
    }

    @Override
    public int rankingInTier() {
        return rankingInTier;
    }

    @Override
    public int mmrChangeToLastGame() {
        return mmrChangeToLastGame;
    }

    @Override
    public int elo() {
        return elo;
    }

    @Override
    public String date() {
        return date;
    }

    @Override
    public String dateRaw() {
        return dateRaw;
    }
}
