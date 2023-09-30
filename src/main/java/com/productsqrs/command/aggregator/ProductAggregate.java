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

    //indentify the aggregate
    @AggregateIdentifier // This annotation is used to identify the aggregate that will handle this command, it will be stored in a bus type and run like a queue
    private String productId;

    private String name;

    private BigDecimal price;

    private Integer quantity;


    @CommandHandler //this command tells us that it is a command handler, that is, the type is identified by the parameters
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductCreateEvent productCreateEvent = ProductCreateEvent.builder().build(); //create the event

        BeanUtils.copyProperties(createProductCommand, productCreateEvent); //coopy the properties from the command to the event
        AggregateLifecycle.apply(productCreateEvent); //add events to the event bus
    }

    @EventSourcingHandler //we work with this event to create the product
    public void on(ProductCreateEvent productCreateEvent) {
        this.productId = productCreateEvent.getProductId();
        this.name = productCreateEvent.getName();
        this.price = productCreateEvent.getPrice();
        this.quantity = productCreateEvent.getQuantity();
    }

}
