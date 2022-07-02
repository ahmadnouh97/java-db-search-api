package com.example.fulltextsearchapi.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping()
    public List<Product> findByDescription(@RequestParam String search) {
        return repository.search(search);
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<Product> findAll(@PageableDefault() Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @RequestMapping("/migrate")
    String migrate() {
        if (repository.count() > 0) {
            return "already migrated";
        }

        try {
            InputStream inputStream = new ClassPathResource("product.csv").getInputStream();

            List<Product> products = ProductReader.readFile(inputStream);
            for (Product product : products) {
                repository.save(product);
            }
            return "success";
        } catch (Exception e){
            System.out.println("Unable to save product: " + e.getMessage());
            return "error";
        }
    }
}