package com.AppGaeBom.sickal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity
@Data
public class Calendar {

    @Id
    @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;

    private LocalDate calendarDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CalendarStatus status;

    private String image;

}
