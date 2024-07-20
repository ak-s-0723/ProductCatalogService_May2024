package org.example.productcatalogservice_may2024.services;

import org.example.productcatalogservice_may2024.clients.FakeStoreClient;
import org.example.productcatalogservice_may2024.dtos.FakeStoreClientProductDto;
import org.example.productcatalogservice_may2024.models.Category;
import org.example.productcatalogservice_may2024.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
//@Primary
public class FakeStoreProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreClient fakeStoreClient;

    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Product getProductBasedOnUserScope(Long productId,Long userId) {
        return null;
    }


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreAPIClient,RedisTemplate<String,Object> redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreAPIClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreClientProductDto[] fakeStoreClientProductDtos =
                restTemplate.getForEntity("http://fakestoreapi.com/products/",
                        FakeStoreClientProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreClientProductDto fakeStoreClientProductDto : fakeStoreClientProductDtos) {
            products.add(getProduct(fakeStoreClientProductDto));
        }

        return products;
    }

    @Override
    public Product getProductById(Long productId) {
        // if product with id found in cache
        //        return from there
        // else make api call to fake store
       //         cache it
       //         return it

        FakeStoreClientProductDto fakeStoreClientProductDto = null;
        fakeStoreClientProductDto = (FakeStoreClientProductDto) redisTemplate.opsForHash().get("PRODUCTS__",productId);
        if(fakeStoreClientProductDto != null) {
            System.out.println("Found in Redis Cache");
            return getProduct(fakeStoreClientProductDto);
        }

        fakeStoreClientProductDto = fakeStoreClient.getProduct(productId);
        System.out.println("Found by calling FakeStore");
        redisTemplate.opsForHash().put("PRODUCTS__",productId, fakeStoreClientProductDto);
        return getProduct(fakeStoreClientProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreClientProductDto fakeStoreClientProductDto = getFakeStoreProductDto(product);
        FakeStoreClientProductDto fakeStoreClientProductDtoResponse = requestForEntity(HttpMethod.PUT,"http://fakestoreapi.com/products/{id}", fakeStoreClientProductDto, FakeStoreClientProductDto.class,id).getBody();
        return getProduct(fakeStoreClientProductDtoResponse);
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    private Product getProduct(FakeStoreClientProductDto fakeStoreClientProductDto) {
        Product product = new Product();
        product.setId(fakeStoreClientProductDto.getId());
        product.setName(fakeStoreClientProductDto.getTitle());
        product.setImageUrl(fakeStoreClientProductDto.getImage());
        product.setPrice(fakeStoreClientProductDto.getPrice());
        product.setDescription(fakeStoreClientProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreClientProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreClientProductDto getFakeStoreProductDto(Product product) {
        FakeStoreClientProductDto fakeStoreClientProductDto = new FakeStoreClientProductDto();
        fakeStoreClientProductDto.setId(product.getId());
        fakeStoreClientProductDto.setDescription(product.getDescription());
        fakeStoreClientProductDto.setPrice(product.getPrice());
        fakeStoreClientProductDto.setImage(product.getImageUrl());
        fakeStoreClientProductDto.setTitle(product.getName());
        if(product.getCategory() != null) {
            fakeStoreClientProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreClientProductDto;
    }
}

