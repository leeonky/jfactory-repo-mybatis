package com.github.leeonky.jfactory.repo.mapper;

import com.github.leeonky.jfactory.repo.entity.Order;

import java.util.List;

public interface OrderMapper {
    void save(Order order);

    List<Order> findAll();
}
