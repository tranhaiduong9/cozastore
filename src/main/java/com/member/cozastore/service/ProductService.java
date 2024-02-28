package com.member.cozastore.service;

import com.member.cozastore.entity.*;
import com.member.cozastore.payload.request.ProductInsertRequest;
import com.member.cozastore.payload.response.ProductResponse;
import com.member.cozastore.repository.ProductRespository;
import com.member.cozastore.service.Imp.DetailImageServiceImp;
import com.member.cozastore.service.Imp.FileStorageServiceImp;
import com.member.cozastore.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    private DetailImageServiceImp detailImageServiceImp;

    @Override
    public boolean insertProduct(ProductInsertRequest productInsertRequest) {
        boolean isSuccess = false;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productInsertRequest.getName());
        productEntity.setImage(productInsertRequest.getImage());
        productEntity.setPrice(productInsertRequest.getPrice());
        productEntity.setDescription(productInsertRequest.getDescription());
        productEntity.setQuantity(productInsertRequest.getQuantity());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(productInsertRequest.getIdCategory());
        productEntity.setCategoryEntity(categoryEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(productInsertRequest.getIdSize());
        productEntity.setSizeEntity(sizeEntity);

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(productInsertRequest.getIdColor());
        productEntity.setColorEntity(colorEntity);
        try {
            productRespository.save(productEntity);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Insert product Error");
        }
        return isSuccess;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> productResponses = new ArrayList<>();
        List<ProductEntity> productEntities = productRespository.findAll();
        for (ProductEntity item : productEntities) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(item.getId());
            productResponse.setName(item.getName());
            productResponse.setImage(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/product/image/").path(item.getImage()).toUriString());
            productResponse.setPrice(item.getPrice());
            productResponse.setDescription(item.getDescription());
            productResponse.setQuantity(item.getQuantity());
            productResponse.setIdCategory(item.getCategoryEntity().getId());
            productResponse.setIdSize(item.getSizeEntity().getId());
            productResponse.setIdColor(item.getColorEntity().getId());
            List<String> detailImageLink = new ArrayList<>();
            for(DetailImageEntity data: item.getDetailImageEntities()){
                detailImageLink.add(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/product/image/").path(data.getName()).toUriString());
            }
            productResponse.setDetailImage(detailImageLink);
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    public byte[] getProductImage(String productImageName) throws IOException {
        return fileStorageServiceImp.loadImage(productImageName);
    }

}
