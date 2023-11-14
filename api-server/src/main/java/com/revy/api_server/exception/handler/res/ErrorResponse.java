package com.revy.api_server.exception.handler.res;

import com.revy.api_server.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

/**
 * Created by Revy on 2023.11.14
 */

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private int code;
    private String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(int code, String message){
        return new ErrorResponse(code, message);
    }
}
