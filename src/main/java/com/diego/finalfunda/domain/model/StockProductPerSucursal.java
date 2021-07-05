package com.diego.finalfunda.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stockproductpersucursals")
public class StockProductPerSucursal   {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Float stock;


    public Long getId() {
        return id;
    }

    public StockProductPerSucursal setId(Long id) {
        this.id = id;
        return this;
    }

    public Float getStock() {
        return stock;
    }

    public StockProductPerSucursal setStock(Float stock) {
        this.stock = stock;
        return this;
    }


}