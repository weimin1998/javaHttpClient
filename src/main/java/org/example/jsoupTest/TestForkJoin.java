package org.example.jsoupTest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestForkJoin {
    public static void main(String[] args) throws IOException {

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("http://127.0.0.1:8080/hello?name=user"+i);
        }

        long start = System.currentTimeMillis();
        for (String url : strings) {
            String body = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute().body();

            System.out.println(body);
        }
        long end = System.currentTimeMillis();

        System.out.println(end-start);


    }


}
