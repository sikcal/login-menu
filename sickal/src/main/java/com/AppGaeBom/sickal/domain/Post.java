package com.AppGaeBom.sickal.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String menu;
    private Long numOfLike;
    private String recipe;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

}
