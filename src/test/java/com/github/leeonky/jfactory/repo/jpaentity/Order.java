package com.github.leeonky.jfactory.repo.jpaentity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long id;
    private String code;
}
