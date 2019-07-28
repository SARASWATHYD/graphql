package com.example.demo.services;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FetchDataFromSE {

    public List<EventDto> fetch() {

        String data = jsonGetRequest("www.google.com");

        Response objectsList = new Gson().fromJson(data, Response.class);

        LinkedTreeMap eventdata = (LinkedTreeMap)objectsList.getData();

        LinkedTreeMap<String, Object> userMap = (LinkedTreeMap<String, Object>) objectsList.getData();
//        ArrayList<EventDto> listevent = (ArrayList<EventDto>)userMap.get("events");

        List<EventDto> listeve = (ArrayList<EventDto>)eventdata.get("events");

        String events = new Gson().toJson(listeve);


       List<EventDto> listevents =fromJSON(events, new TypeToken<List<EventDto>>() {
            }.getType());
        System.out.println(listevents);


        return listevents;

    }

    public static <T> T fromJSON(String data, Type type){
        return  new Gson().fromJson(data,type);
    }



    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

}

