package me.fero.io;

import me.fero.Config;
import me.fero.errors.ApiError;
import me.fero.enums.Error;
import me.fero.errors.ErrorStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.annotation.Nullable;


import static me.fero.Utils.isError;
import static me.fero.Utils.isNull;

public class Response {

    private int code;
    private String rawResponse;
    private JSONObject json;
    private JSONArray jsonArray;

    private Error httpError;
    private ErrorStructure[] errors;

    /**
     * Response Object
     * @param code Response status code from the api
     * @param response Response data (json)
     * @throws ApiError if bad response
     */
    public Response(int code, String response) throws ApiError {
        try {
            this.code = code;
            this.rawResponse = response;

            if(code == 1) return;

            Object parse = Config.parser.parse(response);
            if(parse instanceof JSONArray) {
                this.jsonArray = (JSONArray) parse;
            }
            else {
                this.json = (JSONObject) parse;
            }

            if(isError(code)) {
                this.httpError = switch (code) {
                    case 400 -> Error.BAD_REQUEST;
                    case 429 -> Error.RATE_LIMITED;
                    case 404 -> Error.NOT_FOUND;
                    default -> Error.SERVER_ERROR;
                };

                this.parseErrors(this.json);

                throw new ApiError("API result returned with invalid response", this);
            }
        } catch (ParseException e){
            System.out.println("[-] There was an error parsing the json response.");
            e.printStackTrace();
        }
    }

    private void parseErrors(JSONObject jsonObject) throws ParseException {
        JSONArray originalErrors = (JSONArray) jsonObject.get("errors");
        this.errors = new ErrorStructure[originalErrors.size()];

        for(int i = 0; i < originalErrors.size(); i++) {
            JSONObject e = (JSONObject) Config.parser.parse(originalErrors.get(i).toString());
            this.errors[i] = new ErrorStructure(Integer.parseInt(e.get("code").toString()),
                    e.getOrDefault("details", "").toString(),
                    isNull("global", e) ? null : Boolean.parseBoolean(e.get("global").toString()),
                    e.get("message").toString());
        }
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public JSONObject getJson() {
        return json;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    public Error getHttpError() {
        return this.httpError;
    }

    public ErrorStructure[] getErrors() {
        return this.errors;
    }
}
