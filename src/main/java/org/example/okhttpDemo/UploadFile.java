package org.example.okhttpDemo;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class UploadFile {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("name", "weimin")
                .addFormDataPart("image", "powder.png",
                        RequestBody.create(MediaType.parse("image/png"), new File("src/main/resources/img/powder.jpg"))
                )
                .setType(MultipartBody.FORM)
                .build();

        Request postRequest = new Request.Builder()
                .url("http://localhost:8080/upload")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(postRequest).execute()) {
            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
