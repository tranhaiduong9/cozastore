package com.member.cozastore.service;

import com.member.cozastore.repository.DetailImageRepository;
import com.member.cozastore.service.Imp.DetailImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailImageService implements DetailImageServiceImp {

    @Autowired
    private DetailImageRepository detailImageRepository;

    @Override
    public List<String> getImageNames(int productId) {
        return detailImageRepository.findDetailImageByProductId(productId);
    }
}
