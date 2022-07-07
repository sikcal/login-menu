package com.AppGaeBom.sickal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("log-test")
    public void logTest(){
//LogLevel
        LOGGER.trace("Trace Log");
        //DeBUG레벨보다 더 디테일한 메시지를 표현
        LOGGER.debug("Debug Log");
        //어플리케이션으 디버깅을 위한 메시지 레벨
        LOGGER.info("Info Log");
        //상태변경과 같은 정보성 메시지
        LOGGER.warn("Warn Log");
        //시스템 에러의 원인이 될 수 있는 경고 레벨, 처리 가능한 사항
        LOGGER.error("Error Log");
        //로직 수행 중에 오류가 발생한 경우, 시스템적으로 심각한 문제가 발생하여 작동이 불가한 경우
        //로그레벨
    }
    //그저 exception던져지면 처리해주는
    @PostMapping("/exception")
    public void exceptionTest() throws Exception {
        throw new Exception();
    }
 //이런식으로 하면 밑에 exception(advice)로 넘어가지않고 컨트롤러 단에서 에러처리됌
    //주석처리 해놓으면 exceptionhandling에 의해 밑으로 에러 넘겨짐
    //advice 내 exceptionhandler 호출출    /*
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info(e.getMessage());
        LOGGER.info("Controller 내 ExceptionHandler 호출");

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
