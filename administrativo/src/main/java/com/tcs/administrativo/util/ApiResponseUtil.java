package com.tcs.administrativo.util;

import com.tcs.administrativo.model.dto.ApiResponseDto;

public class ApiResponseUtil {

    public static <T> ApiResponseDto<T> success(String message, T data) {
        return new ApiResponseDto<>("1", message, data);
    }

    public static <T> ApiResponseDto<T> error(String message) {
        return new ApiResponseDto<>("0", message, null);
    }
}
