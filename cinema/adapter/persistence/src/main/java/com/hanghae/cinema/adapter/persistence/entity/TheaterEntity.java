package com.hanghae.cinema.adapter.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_theater")
public class TheaterEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
