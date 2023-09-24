package com.github.leeonky.jfactory.repo.mapper;

import com.github.leeonky.jfactory.repo.entity.OrderLine;

import java.util.List;

public interface OrderLineMapper {

    void save(OrderLine orderLine);

    List<OrderLine> findAll();
}
