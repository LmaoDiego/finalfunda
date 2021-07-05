package com.diego.finalfunda.services;

import com.diego.finalfunda.domain.model.Product;
import com.diego.finalfunda.domain.repository.ProductRepository;
import com.diego.finalfunda.domain.service.ProductService;
import com.diego.finalfunda.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Product getProductById(Long productId) {
        return (Product) this.productRepository.findById(productId).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "Id", productId);
        });
    }

    public Product createProduct(Product product) {
        return (Product) this.productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        return (Product) this.productRepository.findById(productId).map((product) -> {
            product.setName(productDetails.getName());
            return (Product) this.productRepository.save(product);
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "Id", productId);
        });
    }

    public ResponseEntity<?> deleteProduct(Long productId) {
        return (ResponseEntity) this.productRepository.findById(productId).map((product) -> {
            this.productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "Id", productId);
        });
    }
}