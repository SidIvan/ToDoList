package application.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConnectionUtility {
    private static String host;
    public ConnectionUtility() {
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
}
