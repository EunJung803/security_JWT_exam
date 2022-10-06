package com.ll.exam.security_JWT_exam.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
    public static class spring {

        public static <T> ResponseEntity<T> responseEntityOf(HttpHeaders headers) {
            return new ResponseEntity<>(null, headers, HttpStatus.OK);
        }
    }
}