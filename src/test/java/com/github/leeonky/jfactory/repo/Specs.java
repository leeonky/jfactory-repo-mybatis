package com.github.leeonky.jfactory.repo;

import com.github.leeonky.jfactory.Global;
import com.github.leeonky.jfactory.Spec;

public class Specs {
    @Global
    public static class Product extends Spec<com.github.leeonky.jfactory.repo.entity.Product> {
    }

    public static class Order extends Spec<com.github.leeonky.jfactory.repo.entity.Order> {
    }

    public static class OrderLine extends Spec<com.github.leeonky.jfactory.repo.entity.OrderLine> {
    }
}
