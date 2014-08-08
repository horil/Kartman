package com.example.tusharnaik.kartman.NLPEngine;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tushar.naik on 08/08/14.
 */
public class URLdude {

    public static JsonObject convertUrlToJson(String baseurl)
    {
        URL url = null;
        try {
            url = new URL(baseurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = null;
        if(url!=null) {
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream inputStream = null;
        if(urlConnection!=null)
        {
            try {
                inputStream = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(inputStream!=null)
        {
            String response = convertStreamToString(inputStream);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(response);
            return jsonObject;
        }
        return null;
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
