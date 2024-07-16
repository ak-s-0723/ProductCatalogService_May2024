package org.example.productcatalogservice_may2024.clients;

import org.example.productcatalogservice_may2024.dtos.FakeStoreClientProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClientProductDto getProduct(Long productId) {
        FakeStoreClientProductDto fakeStoreClientProductDto = requestForEntity(HttpMethod.GET,"http://fakestoreapi.com/products/{id}",null, FakeStoreClientProductDto.class,productId).getBody();
        return fakeStoreClientProductDto;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
