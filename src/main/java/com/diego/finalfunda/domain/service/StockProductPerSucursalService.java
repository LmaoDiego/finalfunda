package com.diego.finalfunda.domain.service;

import com.diego.finalfunda.domain.model.StockProductPerSucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StockProductPerSucursalService {
    Page<StockProductPerSucursal> getAllStockProductPerSucursals(Pageable pageable);

    StockProductPerSucursal getStockProductPerSucursalById(Long stockProductPerSucursalId);

    StockProductPerSucursal createStockProductPerSucursal(StockProductPerSucursal stockProductPerSucursal);

    StockProductPerSucursal updateStockProductPerSucursal(Long stockProductPerSucursalId, StockProductPerSucursal stockProductPerSucursal);

    ResponseEntity<?> deleteStockProductPerSucursal(Long stockProductPerSucursalId);
}
