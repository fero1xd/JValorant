package me.fero.entities.match.round;

public record DamageEvent(
    String receiverPuuid,
    String receiverDisplayName,
    String receiverTeam,
    Integer bodyShots,
    Integer damage,
    Integer headshots,
    Integer legShots
) {
}
