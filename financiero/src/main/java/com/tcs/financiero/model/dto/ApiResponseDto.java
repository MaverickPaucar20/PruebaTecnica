package com.tcs.financiero.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T>  {
    private String codeResponse;
    private String messageResponse;
    private T data;
}