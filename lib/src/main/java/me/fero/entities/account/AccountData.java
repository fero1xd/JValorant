package me.fero.entities.account;

import me.fero.enums.Region;

public record AccountData(String puuid, Region region, int accountLevel, String name, String tag, Card card,
                          String lastUpdated, long lastUpdatedRaw) {
}
