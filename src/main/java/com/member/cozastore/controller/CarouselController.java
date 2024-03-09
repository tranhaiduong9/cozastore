package com.member.cozastore.controller;

import com.member.cozastore.payload.BaseResponse;
import com.member.cozastore.payload.response.CarouselResponse;
import com.member.cozastore.service.Imp.CarouselServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/carousel")
@CrossOrigin
public class CarouselController {
    @Autowired
    private CarouselServiceImp carouselServiceImp;

    @GetMapping("/{carouselFileName}")
    public ResponseEntity<?> loadCarouselPicture(@PathVariable String carouselFileName) throws IOException {
        byte[] image = carouselServiceImp.loadCarouselPicture(carouselFileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        System.out.println(Arrays.toString(image));
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> loadCarousel() {
        List<CarouselResponse> carouselResponses = carouselServiceImp.findAllCarousel();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Successfully");
        baseResponse.setData(carouselResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
