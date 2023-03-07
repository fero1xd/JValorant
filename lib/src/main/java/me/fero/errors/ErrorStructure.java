package me.fero.errors;

public record ErrorStructure(int code, String details, Boolean global, String message) {
}
