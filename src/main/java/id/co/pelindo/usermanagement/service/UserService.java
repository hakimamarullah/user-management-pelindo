package id.co.pelindo.usermanagement.service;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 6/11/2024 5:10 PM
@Last Modified 6/11/2024 5:10 PM
Version 1.0
*/

import id.co.pelindo.usermanagement.model.User;
import id.co.pelindo.usermanagement.model.response.ApiResponse;

import java.util.List;

public interface UserService {

    ApiResponse<List<User>> getAllUsers();

    ApiResponse<User> getUserById(Integer userId);

    ApiResponse<Boolean> saveUser(User user);

    ApiResponse<Boolean> deleteUserById(Integer userId);
}
