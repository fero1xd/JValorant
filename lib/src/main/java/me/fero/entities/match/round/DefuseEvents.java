package me.fero.entities.match.round;

import me.fero.entities.match.round.events.plant.DoneBy;
import me.fero.entities.match.round.events.plant.PlayerLocation;

public record DefuseEvents(
        Location defuseLocation,
        DoneBy defusedBy,
        Long defuseTimeInRound,
        PlayerLocation[] playerLocationsOnDefuse
) {
}
