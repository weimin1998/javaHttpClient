package org.example.httpClientDemo;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test03_post {
    public static Logger logger = new Logger(Test03_post.class);

    public static void main(String[] args) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        CloseableHttpClient httpClient = httpClientBuilder.build();

        Map<String, Object> params = new HashMap<>();
        params.put("name", "tom");
        params.put("age", 8);

        String jsonString = JSON.toJSONString(params);
        logger.info(jsonString);
        try {
            StringEntity stringEntity = new StringEntity(jsonString);
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/save_user");
            httpPost.setHeader("Content-type", "application/json;charset=utf8");
            httpPost.setEntity(stringEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            logger.info(EntityUtils.toString(entity));
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
