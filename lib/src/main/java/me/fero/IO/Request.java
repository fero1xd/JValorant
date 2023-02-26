package me.fero.IO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    public static Response get(String baseUrl, String apiKey) {
        try {
            URL url = new URL(baseUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            if(apiKey != null) {
                urlConnection.setRequestProperty("X-Riot-Token", apiKey);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            return new Response(urlConnection.getResponseCode(), builder.toString());
        } catch (Exception e) {
            return new Response(1,"Exception caught " + e.getMessage());
        }
    }

    public static Response get(String baseUrl) {
        return get(baseUrl, null);
    }
}
