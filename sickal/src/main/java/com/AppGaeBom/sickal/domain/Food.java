package com.AppGaeBom.sickal.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Food {

    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    private int carbohydrate;
    private int protein;
    private int fat;
    private int total_kcal;
}
