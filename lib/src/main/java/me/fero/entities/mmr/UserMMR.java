package me.fero.entities.mmr;

import javax.annotation.Nullable;

public record UserMMR(int currentTier, String currentTierPatched, Images images, int rankingInTier,
                      int mmrChangeToLastGame, int elo, String name, String tag, boolean old) {
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
    public String name() {
        return name;
    }

    @Override
    public String tag() {
        return tag;
    }

    @Override
    public boolean old() {
        return old;
    }
}
