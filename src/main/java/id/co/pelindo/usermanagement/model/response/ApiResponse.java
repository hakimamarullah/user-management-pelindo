package id.co.pelindo.usermanagement.model.response;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 6/11/2024 5:15 PM
@Last Modified 6/11/2024 5:15 PM
Version 1.0
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T data;

    public static ApiResponse<Void> setSuccess() {
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }

    public  static <T> ApiResponse<T> setSuccess(String message) {
        return ApiResponse.<T>builder()
                .code(200)
                .message(message)
                .build();
    }
}
