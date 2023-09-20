package com.productsqrs.commands;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {

    @TargetAggregateIdentifier // This annotation is used to identify the aggregate that will handle this command
    private String productId;

    private String name;

    private BigDecimal price;

    private Integer quantity;

}
