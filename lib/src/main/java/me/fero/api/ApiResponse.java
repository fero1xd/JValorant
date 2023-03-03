package me.fero.api;

import me.fero.io.Response;

public class ApiResponse {
    private final Response response;

    public ApiResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
