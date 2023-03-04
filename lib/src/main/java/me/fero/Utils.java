package me.fero;

import me.fero.entities.mmr.Images;
import me.fero.entities.mmr.UserMMR;
import me.fero.entities.mmr.UserMMRHistory;
import org.json.simple.JSONObject;

public class Utils {
    public static boolean isError(int code) {
        return (code >= 400 && code <= 429) || code == 500;
    }

    public static UserMMRHistory parseUserMMRHistory(JSONObject data) {
        int currentTier = Integer.parseInt(data.get("currenttier").toString());
        String currentTierPatched = data.get("currenttierpatched").toString();

        int rankingInTier = Integer.parseInt(data.get("ranking_in_tier").toString());
        int mmrChangeToLastGame = Integer.parseInt(data.get("mmr_change_to_last_game").toString());
        int elo = Integer.parseInt(data.get("elo").toString());

        JSONObject imagesRaw = (JSONObject) data.get("images");
        Images images = new Images(imagesRaw.get("small").toString(), imagesRaw.get("large").toString(),
                imagesRaw.get("triangle_down").toString(), imagesRaw.get("triangle_up").toString());

        String date = data.get("date").toString();
        String dateRaw = data.get("date_raw").toString();

        return new UserMMRHistory(currentTier, currentTierPatched, images, rankingInTier, mmrChangeToLastGame,
                elo, date, dateRaw);
    }

    public static UserMMR parseUserMMR(JSONObject data) {
        int currentTier = Integer.parseInt(data.get("currenttier").toString());
        String currentTierPatched = data.get("currenttierpatched").toString();

        int rankingInTier = Integer.parseInt(data.get("ranking_in_tier").toString());
        int mmrChangeToLastGame = Integer.parseInt(data.get("mmr_change_to_last_game").toString());
        int elo = Integer.parseInt(data.get("elo").toString());

        String name = data.get("name").toString();
        String tag = data.get("tag").toString();
        boolean old = Boolean.parseBoolean(data.get("old").toString());

        JSONObject imagesRaw = (JSONObject) data.get("images");
        Images images = new Images(imagesRaw.get("small").toString(), imagesRaw.get("large").toString(),
                imagesRaw.get("triangle_down").toString(), imagesRaw.get("triangle_up").toString());

        return new UserMMR(currentTier, currentTierPatched, images, rankingInTier, mmrChangeToLastGame,
                elo, name, tag, old);
    }

}
