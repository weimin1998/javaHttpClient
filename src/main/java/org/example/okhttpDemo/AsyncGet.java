package org.example.okhttpDemo;

import okhttp3.*;

import java.io.IOException;

public class AsyncGet {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8080/getStudents")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null){
                    System.out.println(response.body().string());
                }
                response.close();
            }
        });


    }
}
