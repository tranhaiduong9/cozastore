package com.member.cozastore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "product")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "idSize")
    private SizeEntity sizeEntity;

    @ManyToOne
    @JoinColumn(name = "idColor")
    private ColorEntity colorEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<CartEntity> cartEntities;

    @OneToMany(mappedBy = "productEntity")
    private List<ProductOrderEntity> productOrderEntities;

    @OneToMany(mappedBy = "productEntity")
    private List<DetailImageEntity> detailImageEntities;
}
