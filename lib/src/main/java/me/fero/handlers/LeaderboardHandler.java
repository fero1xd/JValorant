package me.fero.handlers;

import me.fero.Config;
import me.fero.api.GetLeaderboardResult;
import me.fero.enums.Region;
import me.fero.errors.ApiError;
import me.fero.io.Request;
import me.fero.io.Response;

import java.io.IOException;

public class LeaderboardHandler {

    /**
     * Returns leaderboard data for the given region
     * @param region Leaderboard Region
     * @return Get leaderboard result
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public GetLeaderboardResult getLeaderboard(Region region) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrlV1 + "/leaderboard/" + region.name().toLowerCase());
        return new GetLeaderboardResult(response);
    }

}
