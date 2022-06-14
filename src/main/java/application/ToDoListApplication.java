package application;

import application.Utilities.ConnectionUtility;
import application.Utilities.JsonUtilities;
import application.entities.EventEntity;
import org.aspectj.bridge.ISourceLocation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.HashMap;

@SpringBootApplication
public class ToDoListApplication {
    public static void main(String[] args) throws IOException, FileAlreadyExistsException, InterruptedException {
        SpringApplication a = new SpringApplication(ToDoListApplication.class);
        a.run(args);
        ConnectionUtility connection = new ConnectionUtility();


//        <--CreateEventTest-->
//        String js = "{\"title\":\"accBuing\",\"place\":\"platiRu\",\"text\":\"pro\",\"startDate\":\"2022-06-12\",\"endDate\":\"2022-06-13\",\"startTime\":\"18:00:00\",\"endTime\":\"21:00:00\",\"isLastEvent\":true}";
//        HttpClient client = HttpClient.newHttpClient();
//        System.out.println(connection.getHost() + "/createEvent/");
//        var request = HttpRequest.newBuilder()
//                .uri(URI.create(connection.getHost() + "createEvent/"))
//                .header("accept", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(js))
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());







//        <--JsonUtilities.buildEventJson test-->
//        HashMap<String, String> hm = new HashMap<>();
//        hm.put("title", "accSelling");
//        hm.put("place", "PLATI.RU");
//        hm.put("text", "noob");
//        hm.put("startDate", "2023-07-19");
//        hm.put("endDate", "2023-08-16");
//        hm.put("startTime", "07:13:19");
//        hm.put("endTime", "04:15:25");
//        hm.put("isLastEvent", "true");
//        JsonUtilities.buildEventJson(hm);
    }
}
