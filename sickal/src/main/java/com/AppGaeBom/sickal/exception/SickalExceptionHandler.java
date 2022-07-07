package com.AppGaeBom.sickal.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//exceptionhandler가 프로젝트내 통합적으로 exception 관리
//컨트롤러단에서 나는 에러처리 전부 여기서 해결한다는 뜻
//json형식으로 return{errortype:,code:,message:}
@RestControllerAdvice
public class SickalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(SickalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("Advice 내 ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "식칼 에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
//enum타입으로 만들어놓은것들 member, login 등등
    @ExceptionHandler(value = SickalException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(SickalException e) {
        HttpHeaders responseHeaders = new HttpHeaders();

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("error code",
            Integer.toString(e.getHttpStatusCode())); // Map<String, Object>로 설정하면 toString 불필요
        //다만 코드는 int타입이기에 parse해줘야함요
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
    }

}
