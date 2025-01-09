package com.blogjavaspringboot.domain.mappers;

import com.blogjavaspringboot.domain.dtos.UserDto;
import com.blogjavaspringboot.domain.entities.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    public void testMapUserToUserDto() {
        // Arrange
        User user = new User();
        user.setId(1L);
        Date createdDate = new Date();
        Date lastUpdatedDate = new Date();
        user.setCreated(createdDate);
        user.setLastUpdated(lastUpdatedDate);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("+8801734567890");

        // Act
        UserDto userDto = userMapper.map(user);

        // Assert
        assertNotNull(userDto, "UserDto should not be null");
        assertEquals(user.getId(), userDto.getId(), "ID should match");
        assertEquals(user.getCreated(), userDto.getCreated(), "Created date should match");
        assertEquals(user.getLastUpdated(), userDto.getLastUpdated(), "Last updated date should match");
        assertEquals(user.getFirstName(), userDto.getFirstName(), "First name should match");
        assertEquals(user.getLastName(), userDto.getLastName(), "Last name should match");
        assertEquals(user.getEmail(), userDto.getEmail(), "Email should match");
        assertEquals(user.getPhoneNumber(), userDto.getPhoneNumber(), "Phone number should match");
    }

    @Test
    void testMapUserDtoToUser() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPhoneNumber("+8801734567890");

        User user = null;

        // Act
        user = userMapper.map(user, userDto);

        // Assert
        assertNotNull(user, "User should not be null");
        assertEquals(userDto.getFirstName(), user.getFirstName(), "First name should match");
        assertEquals(userDto.getLastName(), user.getLastName(), "Last name should match");
        assertEquals(userDto.getEmail(), user.getEmail(), "Email should match");
        assertEquals(userDto.getPhoneNumber(), user.getPhoneNumber(), "Phone number should match");
    }

}