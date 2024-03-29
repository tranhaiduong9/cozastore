package com.member.cozastore.repository;

import com.member.cozastore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends JpaRepository<ProductEntity, Integer> {
}
