package me.fero.api;

import me.fero.entities.mmr.UserMMRHistory;
import me.fero.io.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static me.fero.Utils.parseUserMMRHistory;

public class UserMMRHistoryResult extends ApiResponse{

    private final UserMMRHistory[] userMMRHistory;
    private final String name;
    private final String tag;

    public UserMMRHistoryResult(Response response) {
        super(response);

        JSONObject jsonObject = response.getJson();

        JSONArray data = (JSONArray) jsonObject.get("data");
        this.userMMRHistory = new UserMMRHistory[data.size()];

        for (int i = 0; i < data.size(); i++) {
            JSONObject mmr = (JSONObject) data.get(i);
            this.userMMRHistory[i] = parseUserMMRHistory(mmr);
        }

        this.name = jsonObject.get("name").toString();
        this.tag = jsonObject.get("tag").toString();
    }

    public UserMMRHistory[] getUserMMRHistory() {
        return userMMRHistory;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
