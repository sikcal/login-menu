package com.AppGaeBom.sickal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

//프론트에서 로그인시 받는 데이터형식
@Data
@AllArgsConstructor
public class LoginDto {
    @NotEmpty
    private String id;
    @NotEmpty
    private String pw;
}
