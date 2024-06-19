package com.alti.baseTemplate.controllers;

import com.alti.baseTemplate.service.InventoryService;
import com.alti.baseTemplate.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class BaseInventoryRestController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(value = "/product/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId){
        return new ResponseEntity<>(inventoryService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductDTO>>getAllProduct(){
        return new ResponseEntity<>(inventoryService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
            return new ResponseEntity<>(inventoryService.createProduct(productDTO), HttpStatus.OK);
    }
}
