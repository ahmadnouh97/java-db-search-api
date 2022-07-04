package com.example.fulltextsearchapi.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;

    public List<Product> getProducts(Pageable pageable) {
        return new ArrayList<>(productRepository.findAll(pageable).getContent());
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findByName(String name) {
        return productRepository.search(name);
    }

    public Product deleteById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.deleteById(id);
            return product;
        } else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public long count() {
        return productRepository.count();
    }
}
