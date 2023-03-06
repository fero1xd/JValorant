package me.fero.entities.match;

public record TeamStatus(
    Boolean hasWon,
    Integer roundsWon,
    Integer roundsLost
) {
}
