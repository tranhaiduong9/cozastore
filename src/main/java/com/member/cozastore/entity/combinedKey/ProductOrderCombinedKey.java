package com.member.cozastore.entity.combinedKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProductOrderCombinedKey implements Serializable {
    @Column(name = "idProduct",nullable = false)
    private int idProduct;

    @Column(name = "idOrder",nullable = false)
    private int idOrder;
}
