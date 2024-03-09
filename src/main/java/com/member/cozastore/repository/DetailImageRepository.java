package com.member.cozastore.repository;

import com.member.cozastore.entity.DetailImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailImageRepository extends JpaRepository<DetailImageEntity,Integer> {
    @Query("select d.name from detailimage d where d.productEntity.id = :productId")
    List<String> findDetailImageByProductId(int productId);
}
