package com.member.cozastore.entity;

import com.member.cozastore.entity.combinedKey.ProductOrderCombinedKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product_order")
public class ProductOrderEntity {
    @EmbeddedId
    private ProductOrderCombinedKey productOrderCombinedKey;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "idProduct", updatable = false, insertable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "idOrder", updatable = false, insertable = false)
    private OrdersEntity ordersEntity;
}
