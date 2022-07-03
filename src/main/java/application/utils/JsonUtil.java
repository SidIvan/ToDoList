package application.utils;


import application.entities.EventEntity;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

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


    public static String buildEventJson(HashMap<String, String> eventData) {
        JSONObject json = new JSONObject();
        json.putAll(eventData);
        return json.toJSONString();
    }
}
