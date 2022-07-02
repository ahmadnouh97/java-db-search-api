package com.example.fulltextsearchapi.Product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@JsonPropertyOrder({ "url", "name", "code", "highlight", "imageUrl", "category", "price" })
public class Product {

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

    public Product() {
        super();
    }

    public Product(String url, String name, String code, float price) {
        this.url = url;
        this.name = name;
        this.code = code;
        this.price = price;
    }

    @Override
    public String toString() {
        return "url: " + this.url + "\n" +
                "name: " + this.name + "\n" +
                "code: " + this.code + "\n" +
                "price: " + this.price + "\n" +
                "category: " + this.category + "\n" +
                "imageUrl: " + this.imageUrl + "\n" +
                "highlight: " + this.highlight.length() + "\n";
    }
}