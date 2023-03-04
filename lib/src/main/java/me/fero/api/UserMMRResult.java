package me.fero.api;

import me.fero.entities.mmr.UserMMR;
import me.fero.io.Response;
import org.json.simple.JSONObject;

import static me.fero.Utils.parseUserMMR;

public class UserMMRResult extends ApiResponse {

    private final UserMMR userMMR;

    public UserMMRResult(Response response) {
        super(response);

        JSONObject jsonObject = response.getJson();
        JSONObject data = (JSONObject) jsonObject.get("data");

        this.userMMR = parseUserMMR(data);
    }

    public UserMMR getUserMMR() {
        return userMMR;
    }
}
