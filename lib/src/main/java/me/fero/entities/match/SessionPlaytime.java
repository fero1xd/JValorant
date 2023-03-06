package me.fero.entities.match;

public record SessionPlaytime(
        Integer minutes,
        Integer seconds,
        Long milliseconds
) {
}
