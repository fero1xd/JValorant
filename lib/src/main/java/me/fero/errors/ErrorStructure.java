package me.fero.errors;

public record ErrorStructure(int code, String details, boolean global, String message) {
}
