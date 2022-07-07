package com.AppGaeBom.sickal.domain;

import com.AppGaeBom.sickal.dto.MemberDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@Getter
//@Setter //회원이 정상적으로 테이블에 insert되는지를 확인하기 위해 열어둔 Setter || 추후 삭제 예정
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_pw")
    private String password;

    private String name;

    private int height;

    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private MemberSex sex;

    @Enumerated(EnumType.STRING)
    private MemberActivity activity;

    @Enumerated(EnumType.STRING)
    private MemberGoal goal;

    private int weight;

    //member에서 dto로
    public MemberDto toDto(){
        return MemberDto.builder().id(id)
                .activity(activity)
                .birth(birth)
                .goal(goal)
                .height(height)
                .name(name)
                .password(password)
                .sex(sex)
                .weight(weight)
                .build();
    }



}
