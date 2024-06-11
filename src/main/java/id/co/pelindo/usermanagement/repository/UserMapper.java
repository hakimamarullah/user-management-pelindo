package id.co.pelindo.usermanagement.repository;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/13/2024 7:50 PM
@Last Modified 5/13/2024 7:50 PM
Version 1.0
*/

import id.co.pelindo.usermanagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

   List<User> getAll();

   User getById(@Param("userId") Integer userId);

   int insertUser(User user);

   void deleteById(@Param("userId") Integer userId);


}
