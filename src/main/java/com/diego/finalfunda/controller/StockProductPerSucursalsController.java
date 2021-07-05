package com.diego.finalfunda.controller;


import com.diego.finalfunda.domain.model.Product;
import com.diego.finalfunda.domain.model.StockProductPerSucursal;
import com.diego.finalfunda.domain.service.ProductService;
import com.diego.finalfunda.domain.service.StockProductPerSucursalService;
import com.diego.finalfunda.resource.ProductResource;
import com.diego.finalfunda.resource.SaveProductResource;
import com.diego.finalfunda.resource.SaveStockProductPerSucursalResource;
import com.diego.finalfunda.resource.StockProductPerSucursalResource;
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
public class StockProductPerSucursalsController {

    @Autowired
    private StockProductPerSucursalService stockProductPerSucursalService;

    @Autowired
    private ModelMapper mapper;

    public StockProductPerSucursalsController() {
    }

    @Operation(summary = "Get StockProductPerSucursals", description = "Get All StockProductPerSucursals by Page", tags = {"stockproductpersucursals"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All StockProductPerSucursals returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/stockproductpersucursals")
    public Page<StockProductPerSucursalResource> getAllStockProductPerSucursal(Pageable pageable) {
        Page<StockProductPerSucursal> stockProductPerSucursalPage = stockProductPerSucursalService.getAllStockProductPerSucursals(pageable);
        List<StockProductPerSucursalResource> resources = stockProductPerSucursalPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get StockProductPerSucursal by Id", description = "Get StockProductPerSucursal by Id", tags = {"stockproductpersucursals"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All StockProductPerSucursal returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping({"/stockproductpersucursals/{id}"})
    public StockProductPerSucursalResource getStockProductPerSucursalById(@PathVariable(name = "id") Long stockProductPerSucursalId) {
        return this.convertToResource(this.stockProductPerSucursalService.getStockProductPerSucursalById(stockProductPerSucursalId));
    }

    @Operation(summary = "Create a StockProductPerSucursal", description = "Create a StockProductPerSucursal", tags = {"stockproductpersucursals"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "StockProductPerSucursal created", content = @Content(mediaType = "application/json"))

    })
    @PostMapping("/stockproductpersucursals/{stockproductpersucursalId}")
    public StockProductPerSucursalResource createStockProductPerSucursal(@Valid @RequestBody SaveStockProductPerSucursalResource resource) {
        StockProductPerSucursal stockProductPerSucursal = convertToEntity(resource);
        return convertToResource(stockProductPerSucursalService.createStockProductPerSucursal(stockProductPerSucursal));
    }

    @Operation(summary = "Update StockProductPerSucursal", description = "Update StockProductPerSucursal by Id", tags = {"stockproductpersucursals"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update StockProductPerSucursal by Id", content = @Content(mediaType = "application/json"))
    })
    @PutMapping({"/stockproductpersucursals/{id}"})
    public StockProductPerSucursalResource updateStockProductPerSucursal(@PathVariable(name = "id") Long stockProductPerSucursalId, @Valid @RequestBody SaveStockProductPerSucursalResource resource) {
        return this.convertToResource(this.stockProductPerSucursalService.updateStockProductPerSucursal(stockProductPerSucursalId, this.convertToEntity(resource)));
    }

    @Operation(summary = "Delete StockProductPerSucursal", description = "Delete StockProductPerSucursal by Id", tags = {"stockproductpersucursals"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Product by Id", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping({"/stockproductpersucursals/{stockproductpersucursalId}"})
    public ResponseEntity<?> deleteStockProductPerSucursal(@PathVariable Long stockProductPerSucursalId) {
        return this.stockProductPerSucursalService.deleteStockProductPerSucursal(stockProductPerSucursalId);
    }

    private StockProductPerSucursal convertToEntity(SaveStockProductPerSucursalResource resource) {
        return (StockProductPerSucursal) this.mapper.map(resource, StockProductPerSucursal.class);
    }

    private StockProductPerSucursalResource convertToResource(StockProductPerSucursal entity) {
        return (StockProductPerSucursalResource) this.mapper.map(entity, StockProductPerSucursalResource.class);
    }
}