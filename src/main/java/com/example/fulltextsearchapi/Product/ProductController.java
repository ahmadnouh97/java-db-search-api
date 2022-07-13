package com.example.fulltextsearchapi.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.typesense.model.CollectionResponse;
import org.typesense.model.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class ProductController {
    @Autowired
    private ProductService productService;

    @CrossOrigin
    @PostMapping(value = "/products/index")
    public List<HashMap<String, Object>> index(@RequestBody List<Product> products) {
        return productService.index(products);
    }

    @CrossOrigin
    @GetMapping(value = "/products/collection")
    public CollectionResponse info() {
        return productService.getCollection();
    }

    @CrossOrigin
    @GetMapping(value = "/products/documents")
    public SearchResult getDocuments() {
        return productService.getDocuments();
    }

    @CrossOrigin
    @PostMapping(value = "/products/search")
    public SearchResult search(@RequestBody Map<String, String> body) {
        return productService.search(
                body.get("query"),
                body.get("filter"),
                body.get("sort"),
                body.get("facet")
        );
    }

    @CrossOrigin
    @PostMapping(value = "/create")
    public CollectionResponse create(@RequestBody Map<String, String> body) {
        return productService.create(body.get("name"));
    }

    @CrossOrigin
    @DeleteMapping(value = "/drop")
    public CollectionResponse drop(@RequestBody Map<String, String> body) {
        return productService.drop(body.get("name"));
    }

    @CrossOrigin
    @PostMapping(value = "/products/migrate")
    public String migrate(@RequestBody Map<String, String> body) {
        return productService.migrate(body.get("path"));
    }
}
