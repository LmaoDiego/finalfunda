package com.diego.finalfunda.domain.repository;

import com.diego.finalfunda.domain.model.StockProductPerSucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockProductPerSucursalRepository extends JpaRepository<StockProductPerSucursal, Long> {

}
