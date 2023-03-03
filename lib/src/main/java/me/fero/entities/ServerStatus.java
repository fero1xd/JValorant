package me.fero.entities;

import org.json.simple.JSONObject;

public record ServerStatus(JSONObject[] maintenances, JSONObject[] incidents) {
    @Override
    public JSONObject[] maintenances() {
        return maintenances;
    }

    @Override
    public JSONObject[] incidents() {
        return incidents;
    }
}