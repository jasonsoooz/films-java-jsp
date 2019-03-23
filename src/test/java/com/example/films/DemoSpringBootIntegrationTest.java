package com.example.films;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.security.KeyStore;

import static java.util.Collections.singletonList;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(classes = DemoFilmsApplication.class, webEnvironment = DEFINED_PORT)
class DemoSpringBootIntegrationTest {

    private static final String HTTP_SERVER_URL = "http://localhost:8015/demo";
    private static final String HTTPS_SERVER_URL = "https://localhost:8018/demo";
    private static final String PING_URL = HTTPS_SERVER_URL + "/ping";
    private static final String HEALTH_URL = HTTP_SERVER_URL + "/actuator/health";

    @Value("${server.ssl.key-store}")
    private String keystorePath;

//    @Value("$private{server.ssl.key-store-password}")
    @Value("${server.ssl.key-store-password}")
    private char[] keystorePassword;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @DisplayName("application should start and be pingable")
    void applicationShouldStart_andBePingable() throws Exception {
        TestRestTemplate template = createRestTemplate();

        ResponseEntity<String> entity = template.getForEntity(PING_URL, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(OK);
        assertThat(entity.getBody()).contains("Health Check Ping Service");
    }

    @Test
    @DisplayName("application should have management endpoint")
    void applicationShouldHaveAManagementEndpoint() throws Exception {
        TestRestTemplate template = createRestTemplate();

        ResponseEntity<String> entity = template.getForEntity(HEALTH_URL, String.class);
        assertThat(entity.getStatusCode()).isNotEqualTo(NOT_FOUND);
    }

    @Test
    @DisplayName("application should return 404 for bogus url")
    void applicationShouldReturn_404ForBogusUrl() throws Exception {
        TestRestTemplate restTemplate = createRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.postForEntity(new URI(HTTPS_SERVER_URL + "/bogusUrl"), entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    @DisplayName("application should reject https request with no certificate")
    void applicationShouldRejectHttpsRequestWithNoCertificate() throws InterruptedException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            restTemplate.getForEntity(PING_URL, String.class);
            fail();
        } catch (ResourceAccessException expected) {
            Thread.sleep(1000); // wait for the server to do whatever it needs to finish processing the request
            assertThat(expected.getMessage()).contains("javax.net.ssl.SSLHandshakeException: null cert chain");
        }
    }

    private TestRestTemplate createRestTemplate() throws Exception {
        KeyStore keystore = KeyStore.getInstance("jks");
        keystore.load(resourceLoader.getResource(keystorePath).getInputStream(), keystorePassword);

        SSLContext sslContext = new SSLContextBuilder().loadKeyMaterial(keystore, keystorePassword).build();

        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        CloseableHttpClient closeableHttpClient = org.apache.http.impl.client.HttpClientBuilder
                .create()
                .setSSLSocketFactory(connectionFactory)
                .build();

        TestRestTemplate template = new TestRestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = (HttpComponentsClientHttpRequestFactory) template.getRestTemplate().getRequestFactory();
        httpRequestFactory.setHttpClient(closeableHttpClient);
        return template;
    }
}
