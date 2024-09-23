package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // JPA API -> create table book
// JPA API(엔진 : Hibernate)
// object -> table Mapping : ORM 기술(object relationship mapping) -> SQL 생성
public class Book {
    @Id // PK (자동지정이된다)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동증가컬럼 (mysql) - sequnce(oracle)
    private Long id;

    @Column(name="title", unique = true, nullable = false,length = 40)
    private String title; //---> title

    private int price;
    private String author;
    private int page;
}

// Book(table생성용) ,BookDTO 2개만들자
