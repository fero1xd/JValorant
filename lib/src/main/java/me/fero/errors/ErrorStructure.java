package me.fero.errors;

public record ErrorStructure(int code, String details, boolean global, String message) {

    @Override
    public int code() {
        return code;
    }

    @Override
    public String details() {
        return details;
    }

    @Override
    public boolean global() {
        return global;
    }

    @Override
    public String message() {
        return message;
    }
}
