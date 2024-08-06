package com.grs.client;

import com.grs.model.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class FakeStoreClient {

    private final RestClient restClient;

    public FakeStoreClient(@Value("${fakestore.url}") String url) {
        restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public List<Product> getProducts() {
        return restClient.get()
                .uri("/api/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });
    }
}
