package com.member.cozastore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {

        return new ResponseEntity<>("Get all product", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertProduct(){

        return new ResponseEntity<>("Insert Product", HttpStatus.OK);
    }

}
