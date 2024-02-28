package com.member.cozastore.respository;

import com.member.cozastore.entity.CarouselEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselRepository extends JpaRepository<CarouselEntity, Integer> {
}
