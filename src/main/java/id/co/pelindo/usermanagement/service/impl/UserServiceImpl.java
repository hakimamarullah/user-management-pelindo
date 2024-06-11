package id.co.pelindo.usermanagement.service.impl;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 6/11/2024 5:16 PM
@Last Modified 6/11/2024 5:16 PM
Version 1.0
*/

import id.co.pelindo.usermanagement.model.User;
import id.co.pelindo.usermanagement.model.response.ApiResponse;
import id.co.pelindo.usermanagement.repository.UserMapper;
import id.co.pelindo.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> response = ApiResponse.setSuccess("success get all users");
        response.setData(userMapper.getAll());
        return response;
    }

    @Override
    public ApiResponse<User> getUserById(Integer userId) {
        ApiResponse<User> response = ApiResponse.setSuccess("success get user by id");
        response.setData(userMapper.getById(userId));
        return response;
    }

    @Override
    public ApiResponse<Boolean> saveUser(User user) {
        // For the sake of simplicity, we will not hash the password in this mini project
        userMapper.insertUser(user);
        ApiResponse<Boolean> response = ApiResponse.setSuccess("success create user");
        response.setData(true);
        return response;
    }

    @Override
    public ApiResponse<Boolean> deleteUserById(Integer userId) {
        userMapper.deleteById(userId);
        ApiResponse<Boolean> response = ApiResponse.setSuccess("success delete user");
        response.setData(true);
        return response;
    }
}
