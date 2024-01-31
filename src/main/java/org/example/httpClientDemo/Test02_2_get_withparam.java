package org.example.httpClientDemo;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Test02_2_get_withparam {
    public static Logger logger = new Logger(Test02_2_get_withparam.class);

    public static void main(String[] args) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", "jerry"));

        URIBuilder uriBuilder = new URIBuilder();

        try {
            URI uri = uriBuilder.setScheme("http").setHost("127.0.0.1").setPort(8080).setPath("/hello").setParameters(params).build();
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            logger.info(EntityUtils.toString(entity));

        } catch (IOException | URISyntaxException e) {
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
