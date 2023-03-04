package me.fero.entities.mmr;

public record Images(String small, String large, String triangleDown, String triangleUp) {
    @Override
    public String small() {
        return small;
    }

    @Override
    public String large() {
        return large;
    }

    @Override
    public String triangleDown() {
        return triangleDown;
    }

    @Override
    public String triangleUp() {
        return triangleUp;
    }
}
