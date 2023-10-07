package org.example.httpUrlConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/getStudents");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(false);
            // 设置是否从HttpUrlConnection读入
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("GET");
            // 设置是否使用缓存
            connection.setUseCaches(true);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置超时时间
            connection.setConnectTimeout(3000);
            // 连接
            connection.connect();

            StringBuilder msg = new StringBuilder();
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = reader.readLine()) != null) { // 循环从流中读取
                    msg.append(line).append("\n");
                }
                reader.close(); // 关闭流
            }


            connection.disconnect();

            System.out.println(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
