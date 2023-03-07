package me.fero.handlers;

import me.fero.Config;
import me.fero.api.GetMatchDataResult;
import me.fero.api.GetMatchHistoryResult;
import me.fero.enums.MatchFilter;
import me.fero.enums.Region;
import me.fero.errors.ApiError;
import me.fero.interfaces.IMatchHandler;
import me.fero.io.Request;
import me.fero.io.Response;

import java.io.IOException;

public class MatchHandler implements IMatchHandler {

    /**
     * Returns match data for a specific match
     * @param matchId The id of the match
     * @return Match data for a specific match
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    @Override
    public GetMatchDataResult getMatch(String matchId) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrlV2 + "/match/" + matchId);
        return new GetMatchDataResult(response);
    }

    /**
     * Returns the last 5 matches that where played by this user
     * @param name Account name
     * @param tag Account tag
     * @param region Account region
     * @param filter Filter matches by specific mode
     * @return Last 5 matches that where played by this user
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    @Override
    public GetMatchHistoryResult getMatchHistory(String name, String tag, Region region, MatchFilter filter) throws ApiError, IOException {
        String query = filter != MatchFilter.NONE ? "?filter=" + filter.name().toLowerCase() : "";
        Response response = Request.get(Config.unofficialUrlV3 + "/matches/" + region.name().toLowerCase()
                + "/" + name + "/" + tag + query);

        return new GetMatchHistoryResult(response);
    }

    /**
     * Returns the last 5 matches that where played by this user
     * @param name Account name
     * @param tag Account tag
     * @param region Account region
     * @return Last 5 matches that where played by this user
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    @Override
    public GetMatchHistoryResult getMatchHistory(String name, String tag, Region region) throws ApiError, IOException {
        return this.getMatchHistory(name, tag, region, MatchFilter.NONE);
    }

    /**
     * Returns the last 5 matches that where played by this user
     * @param puuid Account puuid
     * @param region Account region
     * @return Last 5 matches that where played by this user
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    @Override
    public GetMatchHistoryResult getMatchHistory(String puuid, Region region, MatchFilter filter) throws ApiError, IOException {
        String query = filter != MatchFilter.NONE ? "?filter=" + filter.name().toLowerCase() : "";
        Response response = Request.get(Config.unofficialUrlV3 + "/by-puuid/matches/" + region.name().toLowerCase()
                + "/" + puuid + query);

        return new GetMatchHistoryResult(response);
    }

    /**
     * Returns the last 5 matches that where played by this user
     * @param puuid Account puuid
     * @param region Account region
     * @return Last 5 matches that where played by this user
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    @Override
    public GetMatchHistoryResult getMatchHistory(String puuid, Region region) throws ApiError, IOException {
        return this.getMatchHistory(puuid, region, MatchFilter.NONE);
    }
}
