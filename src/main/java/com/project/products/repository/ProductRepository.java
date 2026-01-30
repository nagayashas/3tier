package com.project.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.products.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

}
