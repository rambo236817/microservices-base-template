package com.alti.baseTemplate.model;

import java.math.BigDecimal;

public record ProductDTO(Integer productId, String name, ProductType productType, BigDecimal cost) {
}
