package com.yemi.demo.api.util;

import lombok.Data;

@Data
public class CustomError {
    private String errorMsg;

    public CustomError(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
