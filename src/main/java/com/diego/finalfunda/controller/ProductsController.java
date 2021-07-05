package com.diego.finalfunda.controller;


import com.diego.finalfunda.domain.model.Product;
import com.diego.finalfunda.domain.service.ProductService;
import com.diego.finalfunda.resource.ProductResource;
import com.diego.finalfunda.resource.SaveProductResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api"})
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    public ProductsController() {
    }

    @Operation(summary = "Get Products", description = "Get All Products by Page", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/products")
    public Page<ProductResource> getAllProduct(Pageable pageable) {
        Page<Product> productPage = productService.getAllProducts(pageable);
        List<ProductResource> resources = productPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Product by Id", description = "Get Product by Id", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping({"/products/{id}"})
    public ProductResource getProductById(@PathVariable(name = "id") Long productId) {
        return this.convertToResource(this.productService.getProductById(productId));
    }

    @Operation(summary = "Create a Product", description = "Create a Product", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created", content = @Content(mediaType = "application/json"))

    })
    @PostMapping("/products/{productId}")
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource resource) {
        Product product = convertToEntity(resource);
        return convertToResource(productService.createProduct(product));
    }

    @Operation(summary = "Update Product", description = "Update Product by Id", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Product by Id", content = @Content(mediaType = "application/json"))
    })
    @PutMapping({"/products/{id}"})
    public ProductResource updateProduct(@PathVariable(name = "id") Long productId, @Valid @RequestBody SaveProductResource resource) {
        return this.convertToResource(this.productService.updateProduct(productId, this.convertToEntity(resource)));
    }

    @Operation(summary = "Delete Product", description = "Delete Product by Id", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Product by Id", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping({"/products/{productId}"})
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return this.productService.deleteProduct(productId);
    }

    private Product convertToEntity(SaveProductResource resource) {
        return (Product) this.mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity) {
        return (ProductResource) this.mapper.map(entity, ProductResource.class);
    }
}