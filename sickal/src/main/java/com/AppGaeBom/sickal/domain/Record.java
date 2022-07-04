package com.AppGaeBom.sickal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Data
public class Record {

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime recordDate;

}
