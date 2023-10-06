package org.example.okhttpDemo;

import okhttp3.*;

import java.io.IOException;

public class SyncPost {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("id", "4")
                .add("name", "weimin")
                .add("sex", "男")
                .add("age", "20")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/postStudentInfo")
                .post(requestBody)
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
