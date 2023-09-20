package com.productsqrs.models;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProuctRestModel {

    private String productId;

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
