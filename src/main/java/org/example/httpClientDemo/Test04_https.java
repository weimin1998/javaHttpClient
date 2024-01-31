package org.example.httpClientDemo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class Test04_https {
    public static Logger logger = new Logger(Test04_https.class);

    public static void main(String[] args) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8081/hello");
        CloseableHttpResponse response = null;

        try {
            String trustStorePath = "src/main/resources/weimin.p12";
            String trustStorePassword = "weimin";
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(trustStorePath);
            trustStore.load(fis, trustStorePassword.toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            httpClient = httpClientBuilder.setSSLContext(sslContext).build();

            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            logger.info(EntityUtils.toString(entity));

        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException |
                 CertificateException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

    }
}
