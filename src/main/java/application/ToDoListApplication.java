package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Random;

@SpringBootApplication
public class ToDoListApplication {
    public static void main(String[] args) {
        SpringApplication a = new SpringApplication(ToDoListApplication.class);
        a.run(args);
//        File testFile = new File("src/test/java/creationDailyEventTest");
//        File testFile = new File("src/test/java/creationIntervalyEventTest");
//        File testFile = new File("src/test/java/deleteByIdTest");
//        File testFile = new File("src/test/java/creationSingleBirthdayTest");
//        File testFile = new File("src/test/java/deleteAllTest");
//        File testFile = new File("src/test/java/creationRegularBirthdayTest");
//        File testFile = new File("src/test/java/deleteRowByIdTest");
//        File testFile = new File("src/test/java/putTest");
//        File testFile = new File("src/test/java/deleteByTitleTest");
//        File testFile = new File("src/test/java/putNewbornNameTest");
//        File testFile = new File("src/test/java/getByNewbornNameTest");
//        File testFile = new File("src/test/java/getBirthdayByIdTest");
//        Reader fileReader = new FileReader(testFile);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        SimpleInterface test = new SimpleInterface();
//        test.Test(testFile);
    }
}

