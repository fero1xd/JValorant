package me.fero.io;

import me.fero.errors.ApiError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static me.fero.Utils.isError;

public class Request {

    public static Response get(String baseUrl) throws ApiError, IOException {

        URL url = new URL(baseUrl);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("accept", "application/json");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        urlConnection.connect();

        int responseCode = urlConnection.getResponseCode();
        InputStream inputStream;

        if(isError(responseCode)) {
            inputStream = urlConnection.getErrorStream();
        }
        else {
            inputStream = urlConnection.getInputStream();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        reader.close();
        return new Response(urlConnection.getResponseCode(), builder.toString());
    }
}
