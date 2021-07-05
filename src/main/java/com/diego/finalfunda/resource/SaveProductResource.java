package com.diego.finalfunda.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveProductResource {

    @NotNull
    @NotBlank
    @Size(max = 75)
    private String name;



    public String getName() {
        return name;
    }

    public SaveProductResource setName(String name) {
        this.name = name;
        return this;
    }


}
