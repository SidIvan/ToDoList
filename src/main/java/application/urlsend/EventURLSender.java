package application.urlsend;

import application.entities.EventEntity;
import application.utils.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
@Deprecated
public class EventURLSender {
    private final Utils connection = URLSender.connection;
    private final HttpClient client = URLSender.client;
    public int sendPost(String jsonString) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "createEvent/"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseInt(response.body());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public List<EventEntity> sendGetById(int id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getById/" + id + "/"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<EventEntity> res = new ArrayList<>();
            res.add(new EventEntity(response.body()));
//            System.out.println(res.get(0));
            return res;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<EventEntity>();
    }

    public List<EventEntity> sendGetByTitle(String title) {
        title = title.replaceAll(" ", "_");
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getByTitle/" + title + "/"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            List<EventEntity> res = new ArrayList<>();
            return res;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        List<EventEntity> res = new ArrayList<>();
        return res;
    }

    public List<EventEntity> sendGetByDate(String date) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getByDate/" + date.replace('-', '&') + "/"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            List<EventEntity> res = new ArrayList<>();
            return res;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        List<EventEntity> res = new ArrayList<>();
        return res;
    }

    public void sendDeleteById(int id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "deleteById/" + id + "/"))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendDeleteByTitle(String title) {
        title = title.replaceAll(" ", "_");
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "deleteByTitle/" + title + "/"))
                .DELETE()
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendDeleteAll() {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "deleteAll/"))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutNextId(int id, int nextId) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateNextId/" + id + "/" + nextId + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutPrevId(int id, int prevId) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updatePrevId/" + id + "/" + prevId + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutTitle(int id, String title) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateTitle/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(title))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutText(int id, String text) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateText/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(text))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutPlace(int id, String place) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updatePlace/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(place))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutStartDate(int id, String startDate) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateStartDate/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(startDate))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutEndDate(int id, String endDate) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateEndDate/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(endDate))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutStartTime(int id, String startTime) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateStartTime/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(startTime))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendPutEndTime(int id, String endTime) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateEndTime/" + id + "/"))
                .PUT(HttpRequest.BodyPublishers.ofString(endTime))
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
