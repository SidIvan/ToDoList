package application.utils;

import java.util.Random;

public class TokenGenerator {

    public static Random generator = new Random();

    public static String generateToken() {
        String token = "";
        for (int j = 0; j < 5; ++j) {
            for (int i = 0; i < 3; ++i) {
                token += (char) (generator.nextInt(26) + 'a');
            }
            token += (char) (generator.nextInt(10) + '0');
        }
        System.out.println(token);
        return token;
    }
}
