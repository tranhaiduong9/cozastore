package com.member.cozastore.service.Imp;

import com.member.cozastore.payload.response.CarouselResponse;

import java.io.IOException;
import java.util.List;

public interface CarouselServiceImp {
    byte[] loadCarouselPicture(String fileName) throws IOException;
    List<CarouselResponse> findAllCarousel();
}
