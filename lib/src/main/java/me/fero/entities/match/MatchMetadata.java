package me.fero.entities.match;

import me.fero.enums.Region;

public record MatchMetadata(
        String map,
        String gameVersion,
        Long gameLength,
        Long gameStart,
        String gameStartPatched,
        Integer roundsPlayed,
        String mode,
        String queue,
        String seasonId,
        String platform,
        String matchId,
        Region region,
        String cluster
) {
}
