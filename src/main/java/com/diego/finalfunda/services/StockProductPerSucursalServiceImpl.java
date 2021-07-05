package com.diego.finalfunda.services;


import com.diego.finalfunda.domain.model.StockProductPerSucursal;
import com.diego.finalfunda.domain.repository.StockProductPerSucursalRepository;
import com.diego.finalfunda.domain.service.StockProductPerSucursalService;
import com.diego.finalfunda.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockProductPerSucursalServiceImpl implements StockProductPerSucursalService {
    @Autowired
    private StockProductPerSucursalRepository stockProductPerSucursalRepository;


    public Page<StockProductPerSucursal> getAllStockProductPerSucursals(Pageable pageable) {
        return this.stockProductPerSucursalRepository.findAll(pageable);
    }

    public StockProductPerSucursal getStockProductPerSucursalById(Long stockProductPerSucursalId) {
        return (StockProductPerSucursal) this.stockProductPerSucursalRepository.findById(stockProductPerSucursalId).orElseThrow(() -> {
            return new ResourceNotFoundException("StockProductPerSucursal", "Id", stockProductPerSucursalId);
        });
    }

    public StockProductPerSucursal createStockProductPerSucursal(StockProductPerSucursal stockProductPerSucursal) {
        return (StockProductPerSucursal) this.stockProductPerSucursalRepository.save(stockProductPerSucursal);
    }

    public StockProductPerSucursal updateStockProductPerSucursal(Long stockProductPerSucursalId, StockProductPerSucursal stockProductPerSucursalDetails) {
        return (StockProductPerSucursal) this.stockProductPerSucursalRepository.findById(stockProductPerSucursalId).map((stockProductPerSucursal) -> {
            stockProductPerSucursal.setStock(stockProductPerSucursalDetails.getStock());
            return (StockProductPerSucursal) this.stockProductPerSucursalRepository.save(stockProductPerSucursal);
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("StockProductPerSucursal", "Id", stockProductPerSucursalId);
        });
    }

    public ResponseEntity<?> deleteStockProductPerSucursal(Long stockProductPerSucursalId) {
        return (ResponseEntity) this.stockProductPerSucursalRepository.findById(stockProductPerSucursalId).map((stockProductPerSucursal) -> {
            this.stockProductPerSucursalRepository.delete(stockProductPerSucursal);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("StockProductPerSucursal", "Id", stockProductPerSucursalId);
        });
    }
}