package com.example.fulltextsearchapi.Product;

public class Product {
    public Product() {
        super();
    }

    public Product(String url, String name, String code, float price) {
        this.url = url;
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String url;
    public String name;
    public String code;
    public String highlight = "";
    public String imageUrl = "";
    public String category = "";
    public float price;
}
