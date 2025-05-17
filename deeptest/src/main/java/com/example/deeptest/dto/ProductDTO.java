package com.example.deeptest.dto;

import com.example.deeptest.entities.Product;

public class ProductDTO {

    private Long id;
    private String name;
    private Double price;

    public ProductDTO(){}

    public ProductDTO(Long id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductDTO(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
