package com.member.cozastore.repository;

import com.member.cozastore.entity.CarouselEntity;
import com.member.cozastore.entity.DetailImageEntity;
import com.member.cozastore.entity.ProductEntity;
import com.member.cozastore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    @Repository
    interface CarouselRepository extends JpaRepository<CarouselEntity, Integer> {
    }

    @Repository
    interface DetailImageRepository extends JpaRepository<DetailImageEntity,Integer> {
        @Query("select d.name from detailimage d where d.productEntity.id = :productId")
        List<String> findDetailImageByProductId(int productId);
    }

    @Repository
    interface ProductRespository extends JpaRepository<ProductEntity, Integer> {
    }
}
