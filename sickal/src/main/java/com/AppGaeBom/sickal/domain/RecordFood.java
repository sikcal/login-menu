package com.AppGaeBom.sickal.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Data
public class RecordFood {

    @Id
    @GeneratedValue
    @Column(name = "record_food_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

}
