package com.groove.concert_appetizer.common.exception;

import com.groove.concert_appetizer.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 우리가 정의한 비즈니스 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Validation Error: {}", e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    // 예상치 못한 시스템 예외 처리
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e) {
        log.error("Unexpected Error: ", e);
        return ApiResponse.error("서버 내부 오류가 발생했습니다: " + e.getMessage());
    }
}
