package com.alti.baseTemplate.service;

import com.alti.baseTemplate.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    public ProductDTO createProduct(ProductDTO productDTO);
    public ProductDTO getProduct(Long productId);
    public List<ProductDTO> getAllProducts();
    public void deleteProduct(Long productId);
}
