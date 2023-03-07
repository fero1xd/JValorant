package me.fero.api;

import me.fero.entities.match.Match;
import me.fero.io.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static me.fero.Utils.parseMatch;

public class GetMatchHistoryResult extends ApiResponse{

    private final Match[] matches;

    public GetMatchHistoryResult(Response response) {
        super(response);

        JSONArray data = (JSONArray) response.getJson().get("data");
        this.matches = new Match[data.size()];

        for(int i = 0; i < data.size(); i++) {
            this.matches[i] = parseMatch((JSONObject) data.get(i));
        }
    }

    public Match[] getMatches() {
        return matches;
    }
}
