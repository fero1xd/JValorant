package me.fero.entities.account;

public record Card(String small, String large, String wide, String id) {

    @Override
    public String small() {
        return small;
    }

    @Override
    public String large() {
        return large;
    }

    @Override
    public String wide() {
        return wide;
    }

    @Override
    public String id() {
        return id;
    }
}
