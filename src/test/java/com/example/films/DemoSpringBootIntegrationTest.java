package com.example.films;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(classes = DemoFilmsApplication.class, webEnvironment = DEFINED_PORT)
class DemoSpringBootIntegrationTest {

    private static final String SERVER_URL = "http://localhost:8018/demo";
    private static final String PING_URL = SERVER_URL + "/ping";
    private static final String HEALTH_URL = "http://localhost:8015/demo/actuator/health";

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

        ResponseEntity<String> response = restTemplate.postForEntity(new URI(SERVER_URL + "/bogusUrl"), entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    private TestRestTemplate createRestTemplate() throws Exception {
//        KeyStore keystore = KeyStore.getInstance("jks");
//        keystore.load(resourceLoader.getResource(keystorePath).getInputStream(), keystorePassword);
//
//        SSLContext sslContext = new SSLContextBuilder().loadKeyMaterial(keystore, keystorePassword).build();
//
//        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//        CloseableHttpClient closeableHttpClient = org.apache.http.impl.client.HttpClientBuilder
//                .create()
//                .setSSLSocketFactory(connectionFactory)
//                .build();

        TestRestTemplate template = new TestRestTemplate();
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = (HttpComponentsClientHttpRequestFactory) template.getRestTemplate().getRequestFactory();
//        httpRequestFactory.setHttpClient(closeableHttpClient);
        return template;
    }
}
