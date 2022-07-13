package com.example.fulltextsearchapi.Product;

import com.example.fulltextsearchapi.Product.Exceptions.CollectionMigrationException;
import com.example.fulltextsearchapi.Product.Exceptions.CollectionNotFoundException;
import org.springframework.stereotype.Service;
import org.typesense.api.*;
import org.typesense.model.*;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.example.fulltextsearchapi.Product.Exceptions.CollectionAlreadyExistsException;

@Service
public class ProductService {
    private final Client client;
    ProductService() {
        ArrayList<Node> nodes = new ArrayList<>();
        Properties config = Utils.readConfig();
        nodes.add(
                new Node(
                        config.getProperty("db.protocol"),
                        config.getProperty("db.host"),
                        config.getProperty("db.port")
                )
        );

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(2),"xyz");

        this.client = new Client(configuration);
    }

    public Long id;
    public String url;
    public String name;
    public String code;
    public String highlight = "";
    public String imageUrl = "";
    public String category = "";
    public float price;

    public List<HashMap<String, Object>> index(List<Product> products) {
        List<HashMap<String, Object>> documents = new ArrayList<>();

        for (Product product: products) {
            HashMap<String, Object> hmap = new HashMap<>();
            hmap.put("url", product.url);
            hmap.put("name", product.name);
            hmap.put("code", product.code);
            hmap.put("highlight", product.highlight);
            hmap.put("imageUrl", product.imageUrl);
            hmap.put("category", product.category);
            hmap.put("price", product.price);

            try {
                documents.add(
                        client.collections("products").documents().upsert(hmap)
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return documents;
    }


    public CollectionResponse getCollection() {
        try {
            return client.collections("products").retrieve();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SearchResult getDocuments() {
        try {
            return client.collections("products").documents().search(new SearchParameters().q("*").queryBy("name"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public SearchResult search(String query, String filter, String sort, String facet) {
        SearchParameters searchParameters = new SearchParameters()
                .q(query)
                .queryBy("name")
                .filterBy(filter)
                .sortBy(sort)
                .facetBy(facet);

        SearchResult searchResult = null;
        try {
            searchResult = client.collections("products").documents().search(searchParameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return searchResult;
    }

    public CollectionResponse create(String collection) throws CollectionAlreadyExistsException {
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field().name("url").type(FieldTypes.STRING));
        fields.add(new Field().name("name").type(FieldTypes.STRING).locale("ar").infix(Boolean.TRUE).index(Boolean.TRUE).sort(Boolean.TRUE));
        fields.add(new Field().name("code").type(FieldTypes.STRING).locale("en").index(Boolean.TRUE));
        fields.add(new Field().name("highlight").type(FieldTypes.STRING).index(Boolean.TRUE).locale("ar"));
        fields.add(new Field().name("imageUrl").type(FieldTypes.STRING));
        fields.add(new Field().name("category").type(FieldTypes.STRING).locale("ar").facet(Boolean.TRUE));
        fields.add(new Field().name("price").type(FieldTypes.FLOAT).facet(Boolean.TRUE).sort(Boolean.TRUE));

        CollectionSchema collectionSchema = new CollectionSchema();
        collectionSchema.name(collection).fields(fields);

        try {
            return client.collections().create(collectionSchema);
        } catch (Exception e) {
            throw new CollectionAlreadyExistsException("collection already exist");
        }
    }

    public CollectionResponse drop(String collection) {
        try {
            return client.collections(collection).delete();
        } catch (Exception e) {
            throw new CollectionNotFoundException("collection not found");
        }
    }

    public String migrate(String path) {
        ArrayList<HashMap<String, Object>> records = Utils.readJson(path);
        boolean found = client.collections("products") != null;

        if (!found) {
            throw new CollectionNotFoundException("collection not found");
        }
        try {
            return client.collections("products").documents().import_(records, new ImportDocumentsParameters());
        } catch (Exception e) {
            throw new CollectionMigrationException("failed to import documents");
        }
    }
}
