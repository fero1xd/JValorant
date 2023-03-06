package me.fero.entities.match;

import me.fero.entities.match.assets.Assets;
import me.fero.entities.match.platform.Platform;
import me.fero.entities.match.round.AbilityCasts;

public record MatchPlayer(
    String puuid,
    String name,
    String tag,
    String team,
    Integer level,
    String character,
    Integer currentTier,
    String currentTierPatched,
    String playerCard,
    String playerTitle,
    String partyId,
    SessionPlaytime sessionPlaytime,
    Behaviour behaviour,
    Platform platform,
    AbilityCasts abilityCasts,
    Assets assets,
    Stats stats,
    MatchPlayerEconomy economy,
    Integer damageMade,
    Integer damageReceived
) {
}
