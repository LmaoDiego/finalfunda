package com.diego.finalfunda.resource;



public class ProductResource {
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public ProductResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductResource setName(String name) {
        this.name = name;
        return this;
    }


}