package com.productsqrs.query.controller;


import com.productsqrs.command.models.ProductRestModel;
import com.productsqrs.query.queries.GetProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {


    @Autowired
    private QueryGateway queryGateway; //allows you to send the query action to the axon server

    @GetMapping
    public List<ProductRestModel> listProducts(){
        GetProductQuery getProductQuery = new GetProductQuery();
        List<ProductRestModel> products = queryGateway.query( //query method is used to send the query to the axon server
                getProductQuery, //type of query
                ResponseTypes.multipleInstancesOf(ProductRestModel.class) //type of response
        ).join();
        return products;
    }
}
