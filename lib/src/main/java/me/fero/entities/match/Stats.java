package me.fero.entities.match;

public record Stats(
        Integer score,
        Integer kills,
        Integer deaths,
        Integer assists,
        Integer bodyShots,
        Integer headShots,
        Integer legShots
) {
}
