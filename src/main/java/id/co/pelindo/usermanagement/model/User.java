package id.co.pelindo.usermanagement.model;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 6/11/2024 5:05 PM
@Last Modified 6/11/2024 5:05 PM
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class User {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer userId;
    private String username;
    private String namaLengkap;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String status;
}
