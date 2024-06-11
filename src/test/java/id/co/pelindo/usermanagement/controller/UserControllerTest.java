package id.co.pelindo.usermanagement.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import id.co.pelindo.usermanagement.model.User;
import id.co.pelindo.usermanagement.model.response.ApiResponse;
import id.co.pelindo.usermanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = XmlMapper.builder().defaultUseWrapper(false).build();


    @Test
    void whenGetDataUserByIdWithJsonResponseThenSuccess() throws Exception {

        doReturn(ApiResponse.setSuccess()).when(userService).getUserById(1);

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/users?userId=1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        String responseJson = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApiResponse<User> actualResponse = jsonMapper.readValue(responseJson, new TypeReference<>() {
        });

        // Assert
        assertThat(actualResponse, samePropertyValuesAs(ApiResponse.setSuccess()));
        verify(userService).getUserById(1);
    }

    @Test
    void whenGetAllDataUserWithJsonResponseThenSuccess() throws Exception {
        // Mock response data
        ApiResponse<List<User>> mockData = ApiResponse.setSuccess("unit test");
        User user = new User();
        user.setUsername("username");
        user.setPassword("pass");
        user.setUserId(1);
        mockData.setData(List.of(user));

        // Stub service
        doReturn(mockData).when(userService).getAllUsers();

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/users?userId=all")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        String responseJson = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApiResponse<List<User>> actualResponse = jsonMapper.readValue(responseJson, new TypeReference<>() {
        });

        // Assert
        List<User> actualUsers = actualResponse.getData();
        assertEquals(1, actualUsers.size());
        assertThat(actualResponse, samePropertyValuesAs(mockData, "data"));

        User actualUser = actualUsers.get(0);
        assertNull(actualUser.getPassword());
        assertThat(actualUser, samePropertyValuesAs(user, "password"));

        verify(userService).getAllUsers();
    }

    @Test
    void whenGetDataUserByIdWithXmlResponseThenSuccess() throws Exception {
        ApiResponse<User> mockData = ApiResponse.setSuccess("unit test");
        User user = new User();
        user.setUsername("username");
        user.setPassword("pass");
        user.setUserId(1);
        mockData.setData(user);

        doReturn(ApiResponse.setSuccess()).when(userService).getUserById(1);

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/users?userId=1")
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE);

        String responseXML = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApiResponse<User> actualResponse = xmlMapper.readValue(responseXML, new TypeReference<>() {
        });

        // Assert
        assertThat(actualResponse, samePropertyValuesAs(ApiResponse.setSuccess(), "data"));
        verify(userService).getUserById(1);
    }

    @Test
    void whenGetAllDataUserWithXmlResponseThenSuccess() throws Exception {
        // Mock response data
        ApiResponse<List<User>> mockData = ApiResponse.setSuccess("unit test");
        User user = new User();
        user.setUsername("username");
        user.setPassword("pass");
        user.setUserId(1);
        user.setStatus("A");
        user.setNamaLengkap("nama lengkap");
        mockData.setData(List.of(user));

        // Stub service
        doReturn(mockData).when(userService).getAllUsers();

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/users?userId=all")
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE);

        String responseXML = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        ApiResponse<List<User>> actualResponse = xmlMapper.readValue(responseXML, new TypeReference<>() {
        });

        // Assert
        List<User> actualUsers = actualResponse.getData();
        assertEquals(1, actualUsers.size());
        assertThat(actualResponse, samePropertyValuesAs(mockData, "data"));

        User actualUser = actualUsers.get(0);
        assertNull(actualUser.getPassword());
        assertThat(actualUser, samePropertyValuesAs(user, "password"));

        verify(userService).getAllUsers();
    }

    @Test
    void saveUser() {
    }

    @Test
    void deleteUserById() {
    }
}