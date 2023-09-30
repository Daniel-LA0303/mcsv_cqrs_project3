package com.productsqrs.command.models;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


//this class is a model that will be used to received the product details
@Data
@Builder
public class ProductRestModel {

    private String productId;

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
