package me.fero;

import me.fero.enums.Region;
import org.json.simple.parser.JSONParser;

public class Config {
    public static JSONParser parser = new JSONParser();
    public static String unofficialUrl = "https://api.henrikdev.xyz/valorant/v1";

    public static String getOfficialUrl(Region region) {
        return "https://" + region.name() + ".api.riotgames.com";
    }
}
