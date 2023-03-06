package me.fero;

import me.fero.api.AccountDataResult;
import me.fero.errors.ApiError;
import me.fero.api.ServerStatusResult;
import me.fero.handlers.LeaderboardHandler;
import me.fero.handlers.MMRHandler;
import me.fero.handlers.MatchHandler;
import me.fero.io.Request;
import me.fero.io.Response;
import me.fero.enums.Region;

import java.io.IOException;


public class JValorant {
    private final String apiUrl = Config.unofficialUrlV1;

    private final MMRHandler mmrHandler;
    private final MatchHandler matchHandler;
    private final LeaderboardHandler leaderboardHandler;

    /**
    * Default Constructor
    */
    public JValorant() {
        this.mmrHandler = new MMRHandler();
        this.matchHandler = new MatchHandler();
        this.leaderboardHandler = new LeaderboardHandler();
    }

    /**
     * Returns server status for the given region
     * @param region The region for which the status has to be returned
     * @return Server status for that region
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public ServerStatusResult getServerStatus(Region region) throws ApiError, IOException {
        Response response = Request.get(this.apiUrl + "/status/" + region.name().toLowerCase());
        return new ServerStatusResult(response);
    }

    /**
     * Get general account data like puuid and account level
     * @param name Account name
     * @param tag Account tag
     * @return Account data
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public AccountDataResult getAccountData(String name, String tag) throws ApiError, IOException {
        Response response = Request.get(this.apiUrl + "/account/" + name + "/" + tag);
        return new AccountDataResult(response);
    }


    /**
     * Gets the handler that you can use to deal with mmr
     * @return MMR handler for the api
     */
    public MMRHandler getMMRHandler() {
        return mmrHandler;
    }

    /**
     * Gets the handler that you can use to deal with matches
     * @return Match handler for the api
     */
    public MatchHandler getMatchHandler() {
        return matchHandler;
    }


    /**
     * Gets the handler that you can use to deal with leaderboard
     * @return Leaderboard handler for the api
     */
    public LeaderboardHandler getLeaderboardHandler() {
        return leaderboardHandler;
    }
}
