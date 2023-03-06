package me.fero.entities.match;

import me.fero.entities.match.economy.EconomyStat;

public record MatchPlayerEconomy(EconomyStat spent, EconomyStat loadoutValue) {
}
