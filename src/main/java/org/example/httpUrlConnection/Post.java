package org.example.httpUrlConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {
    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080/postStudentInfo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(true);
            // 设置是否从HttpUrlConnection读入
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("POST");

            connection.setConnectTimeout(3000);

            // 设置是否使用缓存
            connection.setUseCaches(false);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 添加 HTTP HEAD 中的一些参数。
            // JDK8中，HttpURLConnection默认开启Keep-Alive
            // connection.setRequestProperty("Connection", "Keep-Alive");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            String params = "id=6&name=weimin&sex=男&age=20";
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();

            // 从连接中读取响应信息
            StringBuilder msg = new StringBuilder();
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    msg.append(line).append("\n");
                }
                reader.close();
            }
            // 5. 断开连接
            connection.disconnect();

            // 处理结果
            System.out.println(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
