package com.member.cozastore.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Controller;

import java.sql.Date;

@Entity(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;
}
