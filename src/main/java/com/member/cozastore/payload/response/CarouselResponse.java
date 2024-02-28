package com.member.cozastore.payload.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarouselResponse {
    private int id;
    private String title;
    private String image;
    private String content;
}
