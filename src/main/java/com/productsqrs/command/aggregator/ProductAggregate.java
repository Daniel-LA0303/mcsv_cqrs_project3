package com.productsqrs.command.aggregator;


import com.productsqrs.command.commands.CreateProductCommand;
import com.productsqrs.command.event.ProductCreateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Aggregate // This annotation say to axon that this class should be administered by axon
public class ProductAggregate {

    @AggregateIdentifier // This annotation is used to identify the aggregate that will handle this command
    private String productId;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductCreateEvent productCreateEvent = ProductCreateEvent.builder().build();

        BeanUtils.copyProperties(createProductCommand, productCreateEvent);
        AggregateLifecycle.apply(productCreateEvent); //add events to the event bus
    }

    @EventSourcingHandler //we create the event
    public void on(ProductCreateEvent productCreateEvent) {
        this.productId = productCreateEvent.getProductId();
        this.name = productCreateEvent.getName();
        this.price = productCreateEvent.getPrice();
        this.quantity = productCreateEvent.getQuantity();
    }

}
