//package com.alti.baseTemplate.service.impl;
//
//import com.alti.baseTemplate.entity.Product;
//import com.alti.baseTemplate.model.ProductType;
//import com.alti.baseTemplate.service.InventoryService;
//import com.alti.baseTemplate.service.repo.ProductRepo;
//import com.alti.baseTemplate.model.ProductDTO;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Component
//public class InventoryServiceImpl implements InventoryService {
//
//    @Autowired
//    private ProductRepo productRepo;
//
//    @Override
//    public ProductDTO createProduct(ProductDTO productDTO) {
//
//        Product product = new Product();
//        BeanUtils.copyProperties(productDTO,product);
//        product.setProductType(productDTO.productType().name());
//        product = productRepo.save(product);
//        BeanUtils.copyProperties(product,productDTO);
//        return productDTO;
//    }
//
//    @Override
//    public ProductDTO getProduct(Long productId) {
//
//       Product product = productRepo.getReferenceById(productId.intValue());
//       return convertToProductDto(product);
//
//    }
//
//    private ProductDTO convertToProductDto(Product product) {
//        return new ProductDTO(product.getProductId().intValue(),product.getName(),
//                ProductType.valueOf(product.getProductType()),product.getCost());
//    }
//
//    @Override
//    public List<ProductDTO> getAllProducts() {
//
//        List<Product> productList = productRepo.findAll();
//        List<ProductDTO> dtoList = new ArrayList<>();
//        productList.forEach(
//               product -> dtoList.add(convertToProductDto(product))
//        );
//        return dtoList;
//    }
//
//    @Override
//    public void deleteProduct(Long productId) {
//
//    }
//}
