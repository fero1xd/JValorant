package me.fero.entities.match.round;

import me.fero.entities.match.round.events.plant.PlantEvents;

public record Round(
        String winningTeam,
        String endType,
        Boolean bombPlanted,
        Boolean bombDefused,
        PlantEvents plantEvents,
        DefuseEvents defuseEvents,
        PlayerStat[] playerStats
) {
}
