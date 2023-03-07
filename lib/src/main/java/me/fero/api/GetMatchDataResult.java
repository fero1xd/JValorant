package me.fero.api;

import me.fero.entities.match.*;
import me.fero.io.Response;
import org.json.simple.JSONObject;

import static me.fero.Utils.parseMatch;


public class GetMatchDataResult extends ApiResponse {

    private final Match match;

    public GetMatchDataResult(Response response) {
        super(response);

        JSONObject data = (JSONObject) response.getJson().get("data");
        this.match = parseMatch(data);
    }

    public Match getMatch() {
        return match;
    }
}
