package me.fero.entities.mmr;

import javax.annotation.Nullable;

public record UserMMR(int currentTier, String currentTierPatched, Images images, int rankingInTier,
                      int mmrChangeToLastGame, int elo, String name, String tag, boolean old) {
}
