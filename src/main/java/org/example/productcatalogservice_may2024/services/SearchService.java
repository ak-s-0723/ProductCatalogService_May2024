package org.example.productcatalogservice_may2024.services;

import org.example.productcatalogservice_may2024.dtos.SortParam;
import org.example.productcatalogservice_may2024.dtos.SortType;
import org.example.productcatalogservice_may2024.models.Product;
import org.example.productcatalogservice_may2024.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams) {

        //Sort sort = Sort.by("price").and(Sort.by("id").descending());
        Sort sort = null;

        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
                //Sort sort = Sort.by("price")
                sort = Sort.by(sortParams.get(0).getParamName());
            }
            else {
                //Sort sort = Sort.by("price").descending()
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
            }
        }

        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC))
                sort.and(Sort.by(sortParams.get(i).getParamName()));
            else
                sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
        }

       return productRepository.findByName(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}
