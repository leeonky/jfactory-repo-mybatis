package com.github.leeonky.jfactory.repo.mapper;

import com.github.leeonky.jfactory.repo.entity.Product;

import java.util.List;

public interface ProductMapper {

    void save(Product product);

    List<Product> findAll();
}
