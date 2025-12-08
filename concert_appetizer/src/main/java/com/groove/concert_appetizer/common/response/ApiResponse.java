package com.groove.concert_appetizer.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;

    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // 성공 시 데이터 반환
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, "Success");
    }

    // 성공 시 메시지만 반환 (void 대용)
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, null, message);
    }

    // 실패 시 (예외 핸들러에서 사용)
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message);
    }
}
