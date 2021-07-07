package com.codesoom.demo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ApiResponseDto<T> {
    public static final ApiResponseDto<String> DEFAULT_OK = new ApiResponseDto<>(ApiResponseCode.OK);
    public static final ApiResponseDto<String> DEFAULT_UNAUTHORIZED = new ApiResponseDto<>(ApiResponseCode.UNAUTHORIZED);

    private ApiResponseCode code;
    private String message;
    private T data;

    private ApiResponseDto(ApiResponseCode status) {
        this.bindStatus(status);
    }

    // 33~35 code 와 관련있음
    private ApiResponseDto(ApiResponseCode status, T data) {
        this.bindStatus(status);
        this.data = data;
    }

    private void bindStatus(ApiResponseCode status) {
        this.code = status;
        this.message = status.getMessage();
    }

    public static <T> ApiResponseDto<T> createOK(T data) {
        return new ApiResponseDto<>(ApiResponseCode.OK, data);
    }
}
