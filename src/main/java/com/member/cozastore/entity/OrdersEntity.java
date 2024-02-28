package com.member.cozastore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "Orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Need link FK
    @Column(name = "idUser")
    private int idUser;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private StatusEntity statusEntity;

    @OneToMany(mappedBy = "ordersEntity")
    private List<ProductOrderEntity> productOrderEntities;
}
