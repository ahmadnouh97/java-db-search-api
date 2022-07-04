package com.example.fulltextsearchapi.Product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@JsonPropertyOrder({ "url", "name", "code", "highlight", "imageUrl", "category", "price" })
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

    @Id
    @GeneratedValue
    public Long id;
    @Column
    public String url;
    @Column
    public String name;
    @Column
    public String code;
    @Column
    public String highlight = "";
    @Column
    public String imageUrl = "";
    @Column
    public String category = "";
    @Column
    public float price;
}