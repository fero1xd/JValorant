package me.fero;

import me.fero.IO.Request;
import me.fero.IO.Response;
import me.fero.enums.Region;


public class JValorant {
    private String apiKey;
    private String apiUrl;

    /**
    * Default Constructor will use unofficial Valorant api url
    */
    public JValorant() {
        this.apiUrl = Config.unofficialUrl;
    }


    /**
     * Constructor to be used with Api key
     * This will make the api use official Valorant api
     * @param apiKey The api key to be used
     * @throws IllegalArgumentException When Api key is an empty string
     */
    public JValorant(String apiKey) {
        if(apiKey.trim().equals("")) {
            throw new IllegalArgumentException("Api key cannot be empty");
        }

        this.apiKey = apiKey;
    }


    /**
     * Sets the api key
     * This will make the client use the official valorant api
     * @param apiKey The api key to be used
     */
    public void setApiKey(String apiKey) {
        if(apiKey.trim().equals("")) {
            throw new IllegalArgumentException("Api key cannot be empty");
        }

        this.apiKey = apiKey;
    }

    public void getServerStatus(Region region) {
        try {
            // Response response = Request.get(this.apiUrl + "/status/" + region.name().toLowerCase());
            Response response = Request.get(Config.getOfficialUrl(region) + "/val/status/v1/platform-data");

            System.out.println(response.getCode());
            System.out.println(response.getRawResponse());
            System.out.println(response.getJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
