package id.co.pelindo.usermanagement.service.impl;

import id.co.pelindo.usermanagement.model.User;
import id.co.pelindo.usermanagement.model.response.ApiResponse;
import id.co.pelindo.usermanagement.repository.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetAllUsersThenShouldReturnCorrectData() {
        // Prepare mock data
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserId(1);
        user.setUsername("unittest");

        users.add(user);

        // Stub user mapper
        doReturn(users).when(userMapper).getAll();

        // Act
        ApiResponse<List<User>> response = userService.getAllUsers();

        // Assert
        assertEquals(200, response.getCode());
        assertEquals("success get all users", response.getMessage());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());

        User actualUser = response.getData().get(0);
        assertThat(actualUser, samePropertyValuesAs(user));

        verify(userMapper).getAll();

    }

    @Test
    void whenGetUserByIdThenShouldReturnValidData() {
        // Prepare mock data
        User user = new User();
        user.setUserId(1);
        user.setUsername("unittest");

        // Stub user mapper
        doReturn(user).when(userMapper).getById(1);

        // Act
        ApiResponse<User> response = userService.getUserById(1);
        ApiResponse<User> response2 = userService.getUserById(2);

        // Assert
        assertEquals(200, response.getCode());
        assertEquals("success get user by id", response.getMessage());
        assertNotNull(response.getData());
        assertNull(response2.getData());


        User actualUser = response.getData();
        assertThat(actualUser, samePropertyValuesAs(user));

        verify(userMapper).getById(1);
        verify(userMapper).getById(2);

    }

    @Test
    void whenSaveUserThenSuccess() {
        // Prepare parameter
        User user = new User();

        // Stub userMapper
        doReturn(1).when(userMapper).insertUser(user);

        // Act
        ApiResponse<Boolean> response = userService.saveUser(user);

        // Assert
        assertEquals(200, response.getCode());
        assertEquals("success create user", response.getMessage());
        assertNotNull(response.getData());
        assertTrue(response.getData());

        verify(userMapper).insertUser(user);
    }

    @Test
    void whenDeleteUserByIdThenSuccess() {
        // Stub userMapper
        doNothing().when(userMapper).deleteById(1);

        // Act
        ApiResponse<Boolean> response = userService.deleteUserById(1);

        // Assert
        assertEquals(200, response.getCode());
        assertEquals("success delete user", response.getMessage());
        assertNotNull(response.getData());
        assertTrue(response.getData());

        verify(userMapper).deleteById(1);
    }
}