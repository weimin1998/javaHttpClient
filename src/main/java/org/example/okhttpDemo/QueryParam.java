package org.example.okhttpDemo;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class QueryParam {
    public static void main(String[] args) {
        getWithId("1");
        getWithId("2");
        getWithId("3");
        getWithId("4");

    }

    private static void getWithId(String id) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.get("http://localhost:8080/getStudentsById")
                .newBuilder()
                .addQueryParameter("id", id)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
