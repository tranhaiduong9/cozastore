package com.member.cozastore.controller;

import com.member.cozastore.payload.BaseResponse;
import com.member.cozastore.payload.request.ProductInsertRequest;
import com.member.cozastore.service.Imp.FileStorageServiceImp;
import com.member.cozastore.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;

    @PostMapping("")
    public ResponseEntity<?> insertProduct(@RequestBody ProductInsertRequest productInsertRequest) {
        BaseResponse baseResponse = new BaseResponse();
        boolean isSuccess = productServiceImp.insertProduct(productInsertRequest);

        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(isSuccess);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(productServiceImp.getAllProduct());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/image/{productImageName}")
    public ResponseEntity<?> getProductImage(@PathVariable String productImageName) throws IOException {
        byte[] image = productServiceImp.getProductImage(productImageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
