package com.productsqrs.command.repository;

import com.productsqrs.command.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
