package com.member.cozastore.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInsertRequest {
    private String name;
    private String image;
    private double price;
    private String description;
    private int quantity;
    private int idCategory;
    private int idSize;
    private int idColor;
}
