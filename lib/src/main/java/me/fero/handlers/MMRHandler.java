package me.fero.handlers;

import me.fero.Config;
import me.fero.api.UserMMRHistoryResult;
import me.fero.api.UserMMRResult;
import me.fero.entities.account.AccountData;
import me.fero.enums.Region;
import me.fero.errors.ApiError;
import me.fero.io.Request;
import me.fero.io.Response;

import java.io.IOException;

public class MMRHandler {

    /**
     * Get mmr data about an user like current tier and last mmr change
     * @param name Account name
     * @param tag Account tag
     * @param region Account region
     * @return User's mmr data
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public UserMMRResult getMMRData(String name, String tag, Region region) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrl + "/mmr/" + region.name().toLowerCase() + "/" + name + "/" + tag);
        return new UserMMRResult(response);
    }

    /**
     * Get mmr data about an user like current tier and last mmr change
     * @param puuid Account's puuid
     * @param region Account region
     * @return User's mmr data
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public UserMMRResult getMMRData(String puuid, Region region) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrl + "/by-puuid/mmr/" + region.name().toLowerCase() + "/" + puuid);
        return new UserMMRResult(response);
    }

    /**
     * Get mmr data about an user like current tier and last mmr change
     * @param account Account Data object
     * @return User's mmr data
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public UserMMRResult getMMRData(AccountData account) throws ApiError, IOException {
        return getMMRData(account.name(), account.tag(), account.region());
    }

    /**
     * Get MMR History of the last competitive matches
     * @param name Account name
     * @param tag Account tag
     * @param region Account Region
     * @return MMR History of the last competitive matches
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public UserMMRHistoryResult getMMRHistory(String name, String tag, Region region) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrl + "/mmr-history/" + region.name().toLowerCase() + "/" +
                name + "/" + tag);
        return new UserMMRHistoryResult(response);
    }

    /**
     * Get MMR History of the last competitive matches
     * @param puuid Account puuid
     * @param region Account Region
     * @return MMR History of the last competitive matches
     * @throws ApiError if bad response
     * @throws IOException if something very bad happens
     */
    public UserMMRHistoryResult getMMRHistory(String puuid, Region region) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrl + "/by-puuid/mmr-history/" + region.name().toLowerCase() + "/" +
                puuid);
        return new UserMMRHistoryResult(response);
    }
}
