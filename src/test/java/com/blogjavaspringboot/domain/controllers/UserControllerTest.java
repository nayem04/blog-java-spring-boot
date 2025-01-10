package com.blogjavaspringboot.domain.controllers;

import com.blogjavaspringboot.common.constants.Msg;
import com.blogjavaspringboot.common.routing.Router;
import com.blogjavaspringboot.domain.dtos.UserDto;
import com.blogjavaspringboot.domain.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockitoBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    private final Long id = 1L;
    private final Date created = new Date();
    private final Date lastUpdated = new Date();
    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String email = "john.doe@example.com";
    private final String phoneNumber = "+8801777777777";

    private final UserDto userDto = new UserDto();

    /*@BeforeEach is an annotation in JUnit 5 (JUnit Jupiter)
     used to specify that a particular method should run
     before each test method in a test class.*/
    @BeforeEach
    void setUp() {
        userDto.setId(id);
        userDto.setCreated(created);
        userDto.setLastUpdated(lastUpdated);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhoneNumber(phoneNumber);
    }

    @Test
    void testSearch() throws Exception {
        // Arrange
        String query = "John";
        int page = 0;
        int pageSize = 10;
        Sort.Direction direction = Sort.Direction.DESC;
        String sortedFieldName = "id";
        Boolean pageableLimit = true;

        Page<UserDto> userDtoPage = new PageImpl<>(List.of(userDto));

        when(userService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit)).thenReturn(userDtoPage);

        // Act && Assert
        mockMvc.perform(get(Router.User.GET_USERS)
                        .param("query", query)
                        .param("page", String.valueOf(page))
                        .param("page_size", String.valueOf(pageSize))
                        .param("direction", direction.name())
                        .param("sorted_field_name", sortedFieldName)
                        .param("pageable_limit", pageableLimit.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(id))
                .andExpect(jsonPath("$.content[0].created").value(created.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.content[0].last_updated").value(lastUpdated.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.content[0].first_name").value(firstName))
                .andExpect(jsonPath("$.content[0].last_name").value(lastName))
                .andExpect(jsonPath("$.content[0].email").value(email))
                .andExpect(jsonPath("$.content[0].phone_number").value(phoneNumber));

        verify(userService, times(1)).search(query, page, pageSize, direction, sortedFieldName, pageableLimit);
    }

    @Test
    void testFind() throws Exception {
        when(userService.find(id)).thenReturn(userDto);

        // Act && Assert
        mockMvc.perform(get(Router.User.GET_USER, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.created").value(created.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.last_updated").value(lastUpdated.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.first_name").value(firstName))
                .andExpect(jsonPath("$.last_name").value(lastName))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.phone_number").value(phoneNumber));

        verify(userService, times(1)).find(id);
    }

    @Test
    void testCreate() throws Exception {
        when(userService.save(any(UserDto.class))).thenReturn(userDto);

        String requestBody = String.format(
                "{\"first_name\":\"%s\",\"last_name\":\"%s\",\"email\":\"%s\",\"phone_number\":\"%s\"}",
                firstName, lastName, email, phoneNumber
        );

        // Act & Assert
        mockMvc.perform(post(Router.User.CREATE_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.last_updated").isNotEmpty())
                .andExpect(jsonPath("$.first_name").value(firstName))
                .andExpect(jsonPath("$.last_name").value(lastName))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.phone_number").value(phoneNumber));

        verify(userService, times(1)).save(any(UserDto.class));
    }


    @Test
    void testUpdate() throws Exception {
        // Arrange
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(id);
        updatedUserDto.setCreated(created);
        updatedUserDto.setLastUpdated(lastUpdated);
        updatedUserDto.setFirstName("UpdatedFirstName");
        updatedUserDto.setLastName("UpdatedLastName");
        updatedUserDto.setEmail("updated.email@example.com");
        updatedUserDto.setPhoneNumber("+8801777777778");

        when(userService.update(eq(id), any(UserDto.class))).thenReturn(updatedUserDto);

        String requestBody = "{\"first_name\":\"UpdatedFirstName\",\"last_name\":\"UpdatedLastName\",\"email\":\"updated.email@example.com\",\"phone_number\":\"+8801777777778\"}";

        // Act & Assert
        mockMvc.perform(patch(Router.User.UPDATE_USER, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.last_updated").isNotEmpty())
                .andExpect(jsonPath("$.first_name").value("UpdatedFirstName"))
                .andExpect(jsonPath("$.last_name").value("UpdatedLastName"))
                .andExpect(jsonPath("$.email").value("updated.email@example.com"))
                .andExpect(jsonPath("$.phone_number").value("+8801777777778"));

        verify(userService, times(1)).update(eq(id), any(UserDto.class));
    }


    @Test
    void testDeleteSoftDelete() throws Exception {
        String successMessage = Msg.Entity.USER + Msg.Response.DELETE;
        when(userService.delete(id, true)).thenReturn(successMessage);

        // Act & Assert
        mockMvc.perform(delete(Router.User.DELETE_USER, id)
                        .param("soft_delete", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(successMessage));

        verify(userService, times(1)).delete(id, true);
    }

    @Test
    void testDeleteHardDelete() throws Exception {
        String successMessage = Msg.Entity.USER + Msg.Response.DELETE;

        when(userService.delete(id, false)).thenReturn(successMessage);

        // Act & Assert
        mockMvc.perform(delete(Router.User.DELETE_USER, id)
                        .param("soft_delete", "false"))
                .andExpect(status().isOk())
                .andExpect(content().string(successMessage));

        verify(userService, times(1)).delete(id, false);
    }

}