package org.example.productcatalogservice_may2024.Stubs;

import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.services.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productservicestub")
public class ProductServiceStub implements IProductService {
    private Map<Long, Product> productMap;

    public ProductServiceStub() {
        productMap = new HashMap<>();
    }

    @Override
    public Product getProductBasedOnUserScope(Long productId,Long userId) {
        return null;
    }


    @Override
    public List<Product> getAllProducts() {
        return (List)productMap.values();
    }

    @Override
    public Product getProductById(Long id) {
       if(productMap.containsKey(id)) {
           return productMap.get(id);
       }

       return null;
    }

    @Override
    public Product createProduct(Product product) {
        productMap.put(product.getId(),product);
        return productMap.get(product.getId());
    }


    @Override
    public Product replaceProduct(Long id, Product product) {
        productMap.put(id,product);
        return productMap.get(id);
    }
}
