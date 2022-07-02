package com.example.fulltextsearchapi.Product;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.InputStream;
import java.util.List;

public class ProductReader {

    public static List<Product> readFile(InputStream inputStream) throws Exception {
        MappingIterator<Product> productItr = new CsvMapper()
                .readerWithTypedSchemaFor(Product.class)
                .readValues(inputStream);

        return productItr.readAll();
    }
}