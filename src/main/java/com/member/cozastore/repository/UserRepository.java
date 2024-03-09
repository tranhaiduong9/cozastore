package com.member.cozastore.repository;

import com.member.cozastore.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    //Set isverify column to 1
    @Transactional
    @Modifying
    @Query("update user u set u.isVerify = 1, u.role.id = 2 WHERE u.id = :id")
    int updateVerifyById(int id);
}
