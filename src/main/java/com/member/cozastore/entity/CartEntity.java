package com.member.cozastore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity productEntity;

    @Column(name = "quantity")
    private int quantity;

    //Need link FK
    @Column(name = "iduser")
    private int idUser;
}
