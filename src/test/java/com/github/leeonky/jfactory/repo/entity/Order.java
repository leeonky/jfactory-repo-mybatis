package com.github.leeonky.jfactory.repo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private long id;
    private String code;
    private List<OrderLine> lines = new ArrayList<>();
}
