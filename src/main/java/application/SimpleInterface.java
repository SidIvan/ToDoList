package application;

import application.entities.EventEntity;
import application.utils.Utils;
import application.utils.JsonUtil;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class SimpleInterface {
    HttpClient client;
    static Utils connection = new Utils();
    static JsonUtil jsonUtil = new JsonUtil();

    SimpleInterface (HttpClient httpClient) {
        client = httpClient;
    }

    HashMap<String, String> readCreateQuery(BufferedReader bufferedReader) {
        String fileString = "";
        HashMap<String, String> jsonBody = new HashMap<>();
        while (true) {
            try {
                fileString = bufferedReader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (fileString.equals("end")) {
                break;
            }
            String[] substrs = fileString.split(": ");
            if (!substrs[0].equals("period")) {
                jsonBody.put(substrs[0], substrs[1]);
            } else if (substrs[1].equals("daily")){
                jsonBody.put("repeatType", "daily");
                jsonBody.put("every", substrs[2]);
                jsonBody.put("times", substrs[3]);
            } else {
                jsonBody.put("repeatType", "intervaly");
                jsonBody.put("every", substrs[2]);
                jsonBody.put("times", substrs[3]);
            }
        }
        if (!jsonBody.containsKey("repeatType")) {
            jsonBody.put("repeatType", "nonRepeatable");
        }
        if (!jsonBody.containsKey("endDate")) {
            jsonBody.put("endDate", jsonBody.get("startDate"));
        }
        return jsonBody;
    }

    HashMap<String, String> readGetQuery(BufferedReader bufferedReader) {
        String fileString = "";
        HashMap<String, String> jsonBody = new HashMap<>();
        while (true) {
            try {
                fileString = bufferedReader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (fileString.equals("end")) {
                break;
            }

            String[] substr = fileString.split(": ");
            jsonBody.put(substr[0], substr[1]);
        }
        return jsonBody;
    }

    HashMap<String, String> readDeleteQuery(BufferedReader bufferedReader) {
        String fileString = "";
        HashMap<String, String> jsonBody = new HashMap<>();
        while (true) {
            try {
                fileString = bufferedReader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (fileString.equals("end")) {
                break;
            }
            if (fileString.equals("all")) {
                return jsonBody;
            }
            String[] substrs = fileString.split(": ");
            jsonBody.put(substrs[0], substrs[1]);
        }
        return jsonBody;
    }

    HashMap<String, String> readPutQuery(BufferedReader bufferedReader) {
        String fileString = "";
        HashMap<String, String> jsonBody = new HashMap<>();
        while (true) {
            try {
                fileString = bufferedReader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (fileString.equals("end")) {
                break;
            }
            String[] substrs = fileString.split(": ");
            jsonBody.put(substrs[0], substrs[1]);
        }
        if (!jsonBody.containsKey("type")) {
            jsonBody.put("type", "single");
        }
        return jsonBody;
    }
    int sendPost(String jsonString) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "createEvent/"))
                .header("accept", "application/json")
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

    List<EventEntity> sendGetById(int id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getById/" + id + "/")).
                header("accept", "application/json")
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

    List<EventEntity> sendGetByTitle(String title) {
        title = title.replaceAll(" ", "_");
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getByTitle/" + title + "/")).
                header("accept", "application/json")
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

    List<EventEntity> sendGetByDate(String date) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "getByDate/" + date.replace('-', '&') + "/"))
                .header("accept", "application/json")
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

    void sendDeleteById(int id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "deleteById/" + id + "/")).
                header("accept", "application/json")
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

    void sendDeleteByTitle(String title) {
        title = title.replaceAll(" ", "_");
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "deleteByTitle/" + title + "/")).
                header("accept", "application/json")
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

    void sendDeleteAll() {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "deleteAll/"))
                .header("accept", "application/json")
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

    void sendPutNextId(int id, int nextId) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateNextId/" + id + "/" + nextId + "/"))
                .header("accept", "application/json")
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

    void sendPutPrevId(int id, int prevId) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updatePrevId/" + id + "/" + prevId + "/"))
                .header("accept", "application/json")
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

    void sendPutTitle(int id, String title) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateTitle/" + id + "/"))
                .header("accept", "application/json")
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

    void sendPutText(int id, String text) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateText/" + id + "/"))
                .header("accept", "application/json")
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

    void sendPutPlace(int id, String place) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updatePlace/" + id + "/"))
                .header("accept", "application/json")
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

    void sendPutStartDate(int id, String startDate) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateStartDate/" + id + "/"))
                .header("accept", "application/json")
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

    void sendPutEndDate(int id, String endDate) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateEndDate/" + id + "/"))
                .header("accept", "application/json")
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

    void sendPutStartTime(int id, String startTime) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateStartTime/" + id + "/"))
                .header("accept", "application/json")
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

    void sendPutEndTime(int id, String endTime) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(connection.getHost() + "updateEndTime/" + id + "/"))
                .header("accept", "application/json")
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

    void sendCreateQuery(HashMap<String, String> eventInfo) {

        if (eventInfo.get("repeatType").equals("nonRepeatable")) {
            eventInfo.remove("repeatType");
            eventInfo.put("prevId", "-1");
            eventInfo.put("nextId", "-1");
            String jsonString = JsonUtil.buildEventJson(eventInfo);
            sendPost(jsonString);
        }

        else if (eventInfo.get("repeatType").equals("intervaly")) {
            int period = parseInt(eventInfo.remove("every")),
                prevEventId = -1,
                curEventId = -1;
            eventInfo.remove("repeatType");
            eventInfo.put("nextId", "-1");
            Date curDate = Date.valueOf(eventInfo.get("startDate"));
            Date endDate = Date.valueOf(eventInfo.get("endDate"));
            if (eventInfo.get("times").charAt(4) != '-') {
                int times = parseInt(eventInfo.remove("times"));
                for (int i = 0; i < times; ++i) {
                    eventInfo.put("prevId", String.valueOf(prevEventId));
                    String jsonString = JsonUtil.buildEventJson(eventInfo);
                    curEventId = sendPost(jsonString);
                    if (prevEventId != -1) {
                        sendPutNextId(prevEventId, curEventId);
                    }
                    curDate.setTime(curDate.getTime() + Utils.getMsInDay() * period);
                    endDate.setTime(endDate.getTime() + Utils.getMsInDay() * period);
                    eventInfo.put("startDate", curDate.toString());
                    eventInfo.put("endDate", endDate.toString());
                    prevEventId = curEventId;
                }
            } else {
                Date borderDate = Date.valueOf(eventInfo.get("times"));
                while (curDate.compareTo(borderDate) < 1) {
                    eventInfo.put("prevId", String.valueOf(prevEventId));
                    String jsonString = JsonUtil.buildEventJson(eventInfo);
                    curEventId = sendPost(jsonString);
                    if (prevEventId != -1) {
                        sendPutNextId(prevEventId, curEventId);
                    }
                    curDate.setTime(curDate.getTime() + Utils.getMsInDay() * period);
                    endDate.setTime(endDate.getTime() + Utils.getMsInDay() * period);
                    eventInfo.put("startDate", curDate.toString());
                    eventInfo.put("endDate", endDate.toString());
                    prevEventId = curEventId;
                }
            }
        }

        else {
            eventInfo.remove("repeatType");
            eventInfo.put("nextId", "-1");
            int prevEventId = -1,
                curEventId = -1;
            boolean[] isNeedToDoThisDay = {false, false, false, false, false, false, false};
            for (int i : stream(eventInfo.remove("every").split(" ")).mapToInt(Integer::parseInt).toArray()) {
                isNeedToDoThisDay[i - 1] = true;
            }
            int curDayOfWeek = (int) (Date.valueOf(eventInfo.get("startDate")).getTime() %
                                      (7 * Utils.getMsInDay()) / Utils.getMsInDay() + 4) % 7;
            Date curDate = Date.valueOf(eventInfo.get("startDate"));
            Date endDate = Date.valueOf(eventInfo.get("endDate"));
            Date borderDate = Date.valueOf(eventInfo.remove("times"));
            while (curDate.compareTo(borderDate) < 1) {
                if (isNeedToDoThisDay[curDayOfWeek]) {
                    eventInfo.put("prevId", String.valueOf(prevEventId));
                    String jsonString = JsonUtil.buildEventJson(eventInfo);
                    curEventId = sendPost(jsonString);
                    if (prevEventId != -1) {
                        sendPutNextId(prevEventId, curEventId);
                    }
                }
                curDate.setTime(curDate.getTime() + Utils.getMsInDay());
                endDate.setTime(endDate.getTime() + Utils.getMsInDay());
                ++curDayOfWeek;
                curDayOfWeek %= 7;
                eventInfo.put("startDate", curDate.toString());
                eventInfo.put("endDate", endDate.toString());
                prevEventId = curEventId;
            }
        }
    }

    List<EventEntity> sendGetQuery(HashMap<String, String> eventInfo) {
        if (eventInfo.containsKey("id")) {
            return sendGetById(parseInt(eventInfo.get("id")));
        } else if (eventInfo.containsKey("title")) {
            return sendGetByTitle(eventInfo.get("title"));
        } else if (eventInfo.containsKey("date")) {
            return sendGetByDate(eventInfo.get("date"));
        }
        return new ArrayList<EventEntity>();
    }

    void sendDeleteQuery(HashMap<String, String> eventInfo) {
        if (eventInfo.get("type").equals("all")) {
            sendDeleteAll();
        } else if (eventInfo.containsKey("id")) {
            EventEntity event = sendGetById(parseInt(eventInfo.get("id"))).get(0);
            if (event.getNextId() != -1) {
                sendPutPrevId(event.getNextId(), event.getPrevId());
            }
            if (event.getPrevId() != -1) {
                sendPutNextId(event.getPrevId(), event.getNextId());
            }
            sendDeleteById(parseInt(eventInfo.get("id")));
            if (eventInfo.get("type").equals("row")) {
                EventEntity eventCopy = event.clone();
                while (eventCopy.getPrevId() != -1) {
                    eventCopy = sendGetById(eventCopy.getPrevId()).get(0);
                    sendDeleteById(eventCopy.getId());
                }
                while (event.getNextId() != -1) {
                    event = sendGetById(event.getNextId()).get(0);
                    sendDeleteById(event.getId());
                }
            }
        } else if (eventInfo.containsKey("title")) {
            sendDeleteByTitle(eventInfo.get("title"));
        }
    }

    void changeEvent(int id, HashMap<String, String> eventInfo) {
        for (String key : eventInfo.keySet()) {
            if (key.equals("title")) {
                sendPutTitle(id, eventInfo.get("title"));
            } else if (key.equals("text")) {
                sendPutText(id, eventInfo.get("text"));
            } else if (key.equals("place")) {
                sendPutPlace(id, eventInfo.get("place"));
            } else if (key.equals("startDate")) {
                sendPutStartDate(id, eventInfo.get("startDate"));
            } else if (key.equals("endDate")) {
                sendPutEndDate(id, eventInfo.get("endDate"));
            } else if (key.equals("startTime")) {
                sendPutStartTime(id, eventInfo.get("startTime"));
            } else if (key.equals("endTime")) {
                sendPutEndTime(id, eventInfo.get("endTime"));
            }
        }
    }

    void sendPutQuery(HashMap<String, String> eventInfo) {
        String type = eventInfo.get("type");
        eventInfo.remove("type");
        EventEntity event = new EventEntity();
        int id = parseInt(eventInfo.get("id"));
        if (type.equals("row")) {
            event = sendGetById(id).get(0);
        }

        changeEvent(id, eventInfo);
        if (type.equals("row")) {
            EventEntity eventCopy = event.clone();
            while (eventCopy.getPrevId() != -1) {
                eventCopy = sendGetById(eventCopy.getPrevId()).get(0);
                changeEvent(eventCopy.getId(), eventInfo);
            }
            while (event.getNextId() != -1) {
                event = sendGetById(event.getNextId()).get(0);
                changeEvent(event.getId(), eventInfo);
            }
        }

    }
    void Test(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String typeOfProc = bufferedReader.readLine();
            if (typeOfProc.equals("create")) {
                HashMap<String, String> eventInfo = readCreateQuery(bufferedReader);
                sendCreateQuery(eventInfo);
            } else if (typeOfProc.equals("get")) {
                HashMap<String, String> eventInfo = readGetQuery(bufferedReader);
                for (var i : sendGetQuery(eventInfo)) {
                    System.out.println(i);
                }
            } else if (typeOfProc.equals("delete")) {
                HashMap<String, String> eventInfo = readDeleteQuery(bufferedReader);
                sendDeleteQuery(eventInfo);
            } else if (typeOfProc.equals("put")) {
                HashMap<String, String> eventInfo = readPutQuery(bufferedReader);
                sendPutQuery(eventInfo);
            }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
