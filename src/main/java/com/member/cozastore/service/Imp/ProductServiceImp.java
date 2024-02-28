package com.member.cozastore.service.Imp;

import com.member.cozastore.payload.request.ProductInsertRequest;
import com.member.cozastore.payload.response.ProductResponse;

import java.io.IOException;
import java.util.List;

public interface ProductServiceImp {
    boolean insertProduct(ProductInsertRequest productInsertRequest);

    List<ProductResponse> getAllProduct();

    byte[] getProductImage(String productImageName) throws IOException;


}
