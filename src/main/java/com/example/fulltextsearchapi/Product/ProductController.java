package com.example.fulltextsearchapi.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ResourceLoader resourceLoader;

    @CrossOrigin
    @GetMapping(value = "")
    public List<Product> findAll(@PageableDefault() Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/search/{name}")
    public List<Product> findByName(@PathVariable("name") String name) {
        return productService.findByName(name);
    }

    @CrossOrigin
    @PostMapping(value = "/index")
    public Product index(@RequestBody Product product) {
        return productService.save(product);
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public Product deleteById(@PathVariable("id") Long id) {
        return productService.deleteById(id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/drop")
    public void drop() {
        productService.deleteAll();
    }

    @RequestMapping("/migrate")
    String migrate() {
        if (productService.count() > 0) {
            return "already migrated";
        }

        try {
            InputStream inputStream = new ClassPathResource("product.csv").getInputStream();

            List<Product> products = ProductReader.readFile(inputStream);
            for (Product product : products) {
                productService.save(product);
            }
            return "success";
        } catch (Exception e){
            System.out.println("Unable to save product: " + e.getMessage());
            return "error";
        }
    }
}