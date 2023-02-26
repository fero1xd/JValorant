package me.fero.IO;

import me.fero.Config;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Response {

    private int code;
    private String rawResponse;
    private JSONObject json;

    /**
     * Response Object
     * @param code Response status code from the api
     * @param response Response data (json)
     */
    public Response(int code, String response) {
        try {
            this.code = code;
            this.rawResponse = response;

            if(code == 1) return;

            this.json = (JSONObject) Config.parser.parse(response);
        } catch (ParseException e){
            System.out.println("[-] There was an error parsing the json response.");
            e.printStackTrace();
        }
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public JSONObject getJson() {
        return json;
    }

    public int getCode() {
        return code;
    }
}
