package com.diego.finalfunda.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveStockProductPerSucursalResource {

    @NotNull
    private Float stock;



    public Float getStock() {
        return stock;
    }

    public SaveStockProductPerSucursalResource setStock(Float stock) {
        this.stock= stock;
        return this;
    }


}