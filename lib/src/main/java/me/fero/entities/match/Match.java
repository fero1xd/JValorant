package me.fero.entities.match;

import me.fero.entities.match.round.Kill;
import me.fero.entities.match.round.Round;

public record Match(
    MatchMetadata metadata,
    Players players,
    TeamsStatus teams,
    Round[] rounds,
    Kill[] kills
) {
}
