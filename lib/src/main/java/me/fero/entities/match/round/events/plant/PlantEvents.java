package me.fero.entities.match.round.events.plant;

import me.fero.entities.match.round.Location;

public record PlantEvents(
        Location plantLocation,
        DoneBy plantedBy,
        String plantSite,
        Long plantTimeInRound,
        PlayerLocation[] playerLocationsOnPlant
) {
}
