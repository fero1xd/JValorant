package me.fero.entities.match.round;

import me.fero.entities.match.round.events.plant.PlayerLocation;

public record KillEvent(
        Long killTimeInRounds,
        Long killTimeInMatch,
        String killerPuuid,
        String killerDisplayName,
        String killerTeam,

        String victimPuuid,
        String victimDisplayName,
        String victimTeam,
        Location victimDeathLocation,
        String damageWeaponId,
        String damageWeaponName,
        WeaponAssets damageWeaponAssets,
        Boolean secondaryFireMode,
        PlayerLocation[] playerLocationsOnKill,
        Assistant[] assistants
) {
}
