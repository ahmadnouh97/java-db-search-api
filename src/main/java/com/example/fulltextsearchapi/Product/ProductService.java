package com.example.fulltextsearchapi.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.typesense.model.CollectionResponse;
import org.typesense.model.SearchResult;


import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;

    public List<HashMap<String, Object>> index(List<Product> products) {
        return productRepository.index(products);
    }

    public CollectionResponse getCollection() {
        return productRepository.getCollection();
    }

    public SearchResult getDocuments() {
        return productRepository.getDocuments();
    }

    public SearchResult search(String query, String filter, String sort, String facet) {
        return productRepository.search(query, filter, sort, facet);
    }

    public CollectionResponse create(String collection) {
        return productRepository.create(collection);
    }
    public CollectionResponse drop(String collection) {
        return productRepository.drop(collection);
    }

    public String migrate(String path) {
        return productRepository.migrate(path);
    }
}
