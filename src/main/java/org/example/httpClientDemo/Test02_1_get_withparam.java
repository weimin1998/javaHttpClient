package org.example.httpClientDemo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Test02_1_get_withparam {
    public static Logger logger = new Logger(Test02_1_get_withparam.class);

    public static void main(String[] args) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        String name = "tom";
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/hello?name=" + name);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
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
