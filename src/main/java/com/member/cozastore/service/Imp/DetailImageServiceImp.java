package com.member.cozastore.service.Imp;

import com.member.cozastore.service.DetailImageService;

import java.util.List;

public interface DetailImageServiceImp{
    List<String> getImageNames(int productId);
}
