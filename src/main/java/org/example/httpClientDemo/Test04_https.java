package org.example.httpClientDemo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
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
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet("https://localhost:8080/hello");

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore ks = KeyStore.getInstance("JKS");
            File trustFile = new File("src/main/resources/server.jks");
            ks.load(new FileInputStream(trustFile), "123456".toCharArray());
            tmf.init(ks);
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLSocketFactory sf = new SSLSocketFactory(sslContext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom().setSSLSocketFactory(sf).build();

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
