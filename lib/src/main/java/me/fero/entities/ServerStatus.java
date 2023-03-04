package me.fero.entities;

import org.json.simple.JSONObject;

public record ServerStatus(JSONObject[] maintenances, JSONObject[] incidents) {
}