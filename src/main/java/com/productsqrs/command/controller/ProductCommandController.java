package com.productsqrs.command.controller;


import com.productsqrs.command.commands.CreateProductCommand;
import com.productsqrs.command.models.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {


    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String svaeProduct(@RequestBody ProductRestModel productRestModel) {
        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                    .productId(UUID.randomUUID().toString())
                    .name(productRestModel.getName())
                    .price(productRestModel.getPrice())
                    .quantity(productRestModel.getQuantity())
                    .build();
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }
}
