package me.fero.api;

import me.fero.Config;
import me.fero.entities.ServerStatus;
import me.fero.io.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 * Server Response for Server status api call
 * TODO: Figure out the type of maintenances and incidents
 */
public class ServerStatusResult extends ApiResponse {
    private final ServerStatus serverStatus;

    public ServerStatusResult(Response response) {
        super(response);

        JSONObject jsonObject = response.getJson();
        JSONObject data = (JSONObject) jsonObject.get("data");

        JSONArray maintenancesRaw = (JSONArray) data.get("maintenances");
        JSONArray incidentsRaw = (JSONArray) data.get("incidents");

        JSONObject[] maintenances = new JSONObject[maintenancesRaw.size()];
        JSONObject[] incidents = new JSONObject[incidentsRaw.size()];

        try {
            for(int i = 0; i < maintenancesRaw.size(); i++) {
                maintenances[i] = (JSONObject) Config.parser.parse(maintenancesRaw.get(i).toString());
            }

            for(int i = 0; i < incidentsRaw.size(); i++) {
                incidents[i] = (JSONObject) Config.parser.parse(incidentsRaw.get(i).toString());
            }
        } catch (ParseException e) {
            System.out.println("[-] Error parsing json");
        }

        this.serverStatus = new ServerStatus(maintenances, incidents);
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }
}
