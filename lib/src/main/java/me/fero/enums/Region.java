package me.fero.enums;

import javax.annotation.Nullable;

public enum Region {
    AP,
    NA,
    EU,
    KR,
    LATAM,
    BR;

    @Nullable
    public static Region findAny(String region) {
        Region[] values = Region.values();
        for (Region value : values) {
            if (value.name().toLowerCase().equalsIgnoreCase(region)) {
                return value;
            }
        }

        return null;
    }
}
