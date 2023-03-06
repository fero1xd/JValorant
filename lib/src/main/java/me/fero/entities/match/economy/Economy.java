package me.fero.entities.match.economy;

import me.fero.entities.match.economy.weapon.Resource;

public record Economy(
        Integer loadoutValue,
        Integer remaining,
        Integer spent,
        Resource weapon,
        Resource armor
) {
}
