package me.fero.entities.account;

import me.fero.enums.Region;

public record AccountData(String puuid, Region region, int accountLevel, String name, String tag, Card card,
                          String lastUpdated, long lastUpdatedRaw) {

    @Override
    public String puuid() {
        return puuid;
    }

    @Override
    public Region region() {
        return region;
    }

    @Override
    public int accountLevel() {
        return accountLevel;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String tag() {
        return tag;
    }

    @Override
    public Card card() {
        return card;
    }

    @Override
    public String lastUpdated() {
        return lastUpdated;
    }

    @Override
    public long lastUpdatedRaw() {
        return lastUpdatedRaw;
    }
}
