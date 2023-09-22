package com.github.leeonky.jfactory.repo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private long id;
    private long orderId, productId;
}
