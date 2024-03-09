package com.member.cozastore.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private int id;
    private String name;
    private String image;
    private double price;
    private String description;
    private int quantity;
    private int idCategory;
    private int idSize;
    private int idColor;
    private List<String> detailImage;
}
