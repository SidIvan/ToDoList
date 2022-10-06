package application.utils;


import application.entities.BirthdayEntity;
import application.entities.EventEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonUtil {

    public String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
                System.out.println(inputLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public URL createURL(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static List<BirthdayEntity> parseBirthdays(String birthdaysData) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(birthdaysData);
            List<BirthdayEntity> birthdayEntities = new ArrayList<>();
            for (Object json : jsonArray) {
                birthdayEntities.add(new BirthdayEntity(((JSONObject) json).toString()));
            }
            return birthdayEntities;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        List<BirthdayEntity> birthdayEntities = new ArrayList<>();
        return birthdayEntities;


    }
    public static String buildEventJson(HashMap<String, String> eventData) {
        JSONObject json = new JSONObject();
        json.putAll(eventData);
        return json.toJSONString();
    }
}
