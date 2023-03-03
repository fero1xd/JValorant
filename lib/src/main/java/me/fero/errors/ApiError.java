package me.fero.errors;

import me.fero.io.Response;

public class ApiError extends Exception {
    private final Response response;

    public ApiError(String msg, Response response) {
        super(msg);
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
