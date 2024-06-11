package id.co.pelindo.usermanagement.controller;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 6/11/2024 5:28 PM
@Last Modified 6/11/2024 5:28 PM
Version 1.0
*/

import id.co.pelindo.usermanagement.model.User;
import id.co.pelindo.usermanagement.model.response.ApiResponse;
import id.co.pelindo.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ApiResponse<?>> getDataUser(@RequestParam String userId) {
        if ("all".equals(userId)) {
            ApiResponse<List<User>> result = userService.getAllUsers();
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getCode()));
        }
        ApiResponse<User> result = userService.getUserById(Integer.parseInt(userId));
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getCode()));
    }

    @PostMapping(value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ApiResponse<Boolean>> saveUser(@RequestBody User user) {
        ApiResponse<Boolean> result = userService.saveUser(user);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getCode()));
    }

    @DeleteMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse<Boolean>> deleteUserById(@RequestParam Integer userId) {
        ApiResponse<Boolean> result = userService.deleteUserById(userId);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getCode()));
    }
}
