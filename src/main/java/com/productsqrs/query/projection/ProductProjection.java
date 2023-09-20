package com.productsqrs.query.projection;

import com.productsqrs.command.data.Product;
import com.productsqrs.command.models.ProductRestModel;
import com.productsqrs.command.repository.ProductRepository;
import com.productsqrs.query.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProjection {


    @Autowired
    private ProductRepository productRepository;


    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery getProductQuery){
        List<Product> products = productRepository.findAll();
        List<ProductRestModel> productRestModels = products.stream()
                .map(product -> ProductRestModel.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build())
                .collect(java.util.stream.Collectors.toList());
        return productRestModels;
    }
}
