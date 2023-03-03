package me.fero;

import me.fero.api.AccountDataResult;
import me.fero.errors.ApiError;
import me.fero.api.ServerStatusResult;
import me.fero.io.Request;
import me.fero.io.Response;
import me.fero.enums.Region;


public class JValorant {
    private final String apiUrl = Config.unofficialUrl;

    /**
    * Default Constructor
    */
    public JValorant() {
    }


    /**
     * Returns server status for the given region
     * @param region The region for which the status has to be returned
     * @throws ApiError if bad response
     */
    public ServerStatusResult getServerStatus(Region region) throws ApiError {
        Response response = Request.get(this.apiUrl + "/status/" + region.name().toLowerCase());
        return new ServerStatusResult(response, response.getJson());
    }

    /**
     * Get general account data like puuid and account level
     * @param name Account name
     * @param tag Account tag
     * @throws ApiError if bad response
     */
    public AccountDataResult getAccountData(String name, String tag) throws ApiError {
        Response response = Request.get(this.apiUrl + "/account/" + name + "/" + tag);
        return new AccountDataResult(response, response.getJson());
    }
}
