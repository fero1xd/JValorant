package me.fero.entities.match.round.events.plant;

import me.fero.entities.match.round.Location;

public record PlayerLocation(String playerPuuid, String playerDisplayName,
                             String playerTeam,
                             Location location,
                             Double viewRadians) {
}
