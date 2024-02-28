package com.vottun.SDK.Test.service;

import com.vottun.SDK.Test.model.DeployERC20Request;
import com.vottun.SDK.Test.model.GetNameRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ERC20Service {

    @Value("${api.url}")
    private String apiUrl;
    @Value("${api.key}")
    private String apiKey;
    @Value("${app.id}")
    private String appId;

    private final RestTemplate restTemplate;

    public ERC20Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object deployToken(DeployERC20Request tokenRequest) {
        return performHttpRequest("/erc/v1/erc20/deploy", tokenRequest, "Error deploying token");
    }

    public Object getName(GetNameRequest getNameRequest) {
        return performHttpRequest("/erc/v1/erc20/name", getNameRequest, "Error getting token name");
    }

    private Object performHttpRequest(String endpoint, Object requestBody, String errorMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("x-application-vkn", appId);

        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                apiUrl + endpoint,
                HttpMethod.POST,
                requestEntity,
                Object.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody(); // Return response if successful
        } else {
            throw new RuntimeException(errorMessage + ": " + responseEntity.getStatusCode());
        }
    }
}

