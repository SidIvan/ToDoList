package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

@Deprecated
public class QueryReader {
    public HashMap<String, String> readCreateQuery(BufferedReader bufferedReader) {
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
            if (substrs[0].equals("times")) {
                jsonBody.put("repeatType", "intervaly");
                jsonBody.put("every", "1");
                jsonBody.put("times", substrs[1]);

            } else if (!substrs[0].equals("period")) {
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

    public HashMap<String, String> readGetQuery(BufferedReader bufferedReader) {
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
        if (!jsonBody.containsKey("type")) {
            if (jsonBody.containsKey("newborn name") && !jsonBody.containsKey("id")) {
                jsonBody.put("type", "allSame");
            } else {
                jsonBody.put("type", "single");
            }
        }
        return jsonBody;
    }

    public HashMap<String, String> readDeleteQuery(BufferedReader bufferedReader) {
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
                jsonBody.put("type", "all");
                return jsonBody;
            }
            String[] substrs = fileString.split(": ");
            jsonBody.put(substrs[0], substrs[1]);
            if (substrs[0].equals("title")) {
                jsonBody.put("type", "row");
            }
        }
        return jsonBody;
    }

    public HashMap<String, String> readPutQuery(BufferedReader bufferedReader) {
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
        return jsonBody;
    }
}
