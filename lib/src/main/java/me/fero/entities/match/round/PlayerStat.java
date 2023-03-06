package me.fero.entities.match.round;

import me.fero.entities.match.economy.Economy;

public record PlayerStat(
        String puuid,
        String displayName,
        String team,
        Integer damage,
        Integer bodyShots,
        Integer headShots,
        Integer legShots,
        Integer kills,
        Integer score,
        Boolean wasAfk,
        Boolean wasPenalized,
        Boolean stayedInSpawn,
        DamageEvent[] damageEvents,
        KillEvent[] killEvents,
        AbilityCasts abilityCasts,
        Economy economy
) {
}
