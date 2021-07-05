package com.diego.finalfunda.resource;

public class StockProductPerSucursalResource {

    private Long id;
    private Float stock;


    public Long getId() {
        return id;
    }

    public StockProductPerSucursalResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Float getStock() {
        return stock;
    }

    public StockProductPerSucursalResource setName(Float stock) {
        this.stock = stock;
        return this;
    }
}
