package com.member.cozastore.service;

import com.member.cozastore.entity.CarouselEntity;
import com.member.cozastore.payload.response.CarouselResponse;
import com.member.cozastore.repository.CarouselRepository;
import com.member.cozastore.service.Imp.CarouselServiceImp;
import com.member.cozastore.service.Imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselService implements CarouselServiceImp {
    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    private CarouselRepository carouselRepository;

    @Override
    public byte[] loadCarouselPicture(String fileName) throws IOException {
        return fileStorageServiceImp.loadImage(fileName);
    }

    @Override
    public List<CarouselResponse> findAllCarousel() {
        List<CarouselEntity> carouselEntities = carouselRepository.findAll();
        List<CarouselResponse> carouselResponses = new ArrayList<>();
        for(CarouselEntity item: carouselEntities){
            CarouselResponse carouselResponse = new CarouselResponse();
            carouselResponse.setId(item.getId());
            carouselResponse.setContent(item.getContent());
            carouselResponse.setTitle(item.getTitle());
            carouselResponse.setImage(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/carousel/").path(item.getImage()).toUriString());
            carouselResponses.add(carouselResponse);
        }
        return carouselResponses;
    }
}
