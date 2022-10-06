package application.urlsend;

import application.entities.BirthdayEntity;
import application.utils.JsonUtil;
import application.utils.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BirthdayURLSender {
    private final static Utils connection = URLSender.connection;
    private final HttpClient client = URLSender.client;

    public void sendPost(String jsonString) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "createBirthday/"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public List<BirthdayEntity> sendGetById(String id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getBirthdayById/" + id + "/"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            List<BirthdayEntity> birthdayEntities = JsonUtil.parseBirthdays(response.body());
            return birthdayEntities;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        List<BirthdayEntity> birthdayEntities = new ArrayList<>();
        return birthdayEntities;
    }
    public List<BirthdayEntity> sendGetByName(String name) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getByName/" + name + "/"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            List<BirthdayEntity> birthdayEntities = JsonUtil.parseBirthdays(response.body());
            return birthdayEntities;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        List<BirthdayEntity> birthdayEntities = new ArrayList<>();
        return birthdayEntities;
    }

    public void sendPutName(int id, String name) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "putName/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(name))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
