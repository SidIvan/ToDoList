package application.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    private static String host;
    private final static long MS_IN_DAY = 86400000;
    public Utils() {
        try {
            File file = new File("src/main/resources/host");
            FileReader symbolReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(symbolReader);
            host = reader.readLine();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getHost() {
        return host;
    }

    public static long getMsInDay() { return MS_IN_DAY; }
}
