package com.member.cozastore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryEntity")
    private List<CarouselEntity> carouselEntities;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities;
}
