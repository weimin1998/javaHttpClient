package org.example.okhttpDemo;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class SyncPostJson {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "5");
        jsonObject.put("name", "weimin");
        jsonObject.put("sex", "男");
        jsonObject.put("age", "20");

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                jsonObject.toString()
        );

        Request request = new Request.Builder()
                .url("http://localhost:8080/postStudentInfoJson")
                .post(requestBody)
                .build();

        try(Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
