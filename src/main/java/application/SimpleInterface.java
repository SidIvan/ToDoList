package application;

import application.entities.EventEntity;
import application.urlsend.BirthdayURLSender;
import application.urlsend.EventURLSender;
import application.utils.Utils;
import application.utils.JsonUtil;
import application.urlsend.EventURLSender;

import javax.management.Query;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class SimpleInterface {
    private static QueryReader queryReader = new QueryReader();
    private static QuerySender querySender = new QuerySender();

    SimpleInterface () {
    }

    void Test(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String typeOfProc = bufferedReader.readLine();
            if (typeOfProc.equals("create")) {
                HashMap<String, String> eventInfo = queryReader.readCreateQuery(bufferedReader);
                querySender.sendCreateQuery(eventInfo);
            } else if (typeOfProc.equals("get")) {
                HashMap<String, String> eventInfo = queryReader.readGetQuery(bufferedReader);
                if (eventInfo.containsKey("newborn name")) {
                    for (var i : querySender.sendGetBirthdaysQuery(eventInfo)) {
                        System.out.println(i);
                    }
                }
                for (var i : querySender.sendGetEventsQuery(eventInfo)) {
                    System.out.println(i);
                }
            } else if (typeOfProc.equals("delete")) {
                HashMap<String, String> eventInfo = queryReader.readDeleteQuery(bufferedReader);
                querySender.sendDeleteQuery(eventInfo);
            } else if (typeOfProc.equals("put")) {
                HashMap<String, String> eventInfo = queryReader.readPutQuery(bufferedReader);
                querySender.sendPutQuery(eventInfo);
            }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
