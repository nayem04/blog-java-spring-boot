package com.blogjavaspringboot.domain.services;

import com.blogjavaspringboot.common.constants.Msg;
import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.domain.dtos.UserDto;
import com.blogjavaspringboot.domain.entities.User;
import com.blogjavaspringboot.domain.mappers.UserMapper;
import com.blogjavaspringboot.domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final Long id = 1L;
    private final Date created = new Date();
    private final Date lastUpdated = new Date();
    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String email = "john.doe@example.com";
    private final String phoneNumber = "+8801777777777";

    private final String idMessage = "Id should match.";
    private final String createdMessage = "Created should match.";
    private final String lastUpdatedMessage = "LastUpdated should match.";
    private final String firstNameMessage = "FirstName should match.";
    private final String lastNameMessage = "LastName should match.";
    private final String emailMessage = "Email should match.";
    private final String phoneNumberMessage = "PhoneNumber should match.";

    @Test
    void testSearch() {
        // Arrange
        String query = "John";
        int page = 0;
        int pageSize = 10;
        Sort.Direction direction = Sort.Direction.DESC;
        String sortedFieldName = "id";
        Boolean pageableLimit = true;

        User user = new User();
        user.setId(id);
        user.setCreated(created);
        user.setLastUpdated(lastUpdated);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setCreated(created);
        userDto.setLastUpdated(lastUpdated);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhoneNumber(phoneNumber);

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(direction, sortedFieldName));
        Page<User> userPage = new PageImpl<>(List.of(user));

        when(userRepository.search(query, pageRequest)).thenReturn(userPage);
        when(userMapper.map(user)).thenReturn(userDto);

        // Act
        Page<UserDto> userDtoPageResult = userService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit);

        // Assert
        assertNotNull(userDtoPageResult, "UserDtoPageResult should not be null");
        assertEquals(1, userDtoPageResult.getContent().size(), "UserDtoPageResult.getContent() size should be 1");
        assertEquals(id, userDtoPageResult.getContent().getFirst().getId(), idMessage);
        assertEquals(created, userDtoPageResult.getContent().getFirst().getCreated(), createdMessage);
        assertEquals(lastUpdated, userDtoPageResult.getContent().getFirst().getLastUpdated(), lastUpdatedMessage);
        assertEquals(firstName, userDtoPageResult.getContent().getFirst().getFirstName(), firstNameMessage);
        assertEquals(lastName, userDtoPageResult.getContent().getFirst().getLastName(), lastNameMessage);
        assertEquals(email, userDtoPageResult.getContent().getFirst().getEmail(), emailMessage);
        assertEquals(phoneNumber, userDtoPageResult.getContent().getFirst().getPhoneNumber(), phoneNumberMessage);

        verify(userRepository, times(1)).search(query, pageRequest);
        verify(userMapper, times(1)).map(user);
    }

    @Test
    void find() throws NotFoundException {
        // Arrange
        User user = new User();
        user.setId(id);
        user.setCreated(created);
        user.setLastUpdated(lastUpdated);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setEmail(phoneNumber);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setCreated(created);
        userDto.setLastUpdated(lastUpdated);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhoneNumber(phoneNumber);

        when(userRepository.find(id)).thenReturn(Optional.of(user));
        when(userMapper.map(user)).thenReturn(userDto);

        // Act
        UserDto userDtoResult = userService.find(id);

        // Assert
        assertNotNull(userDtoResult, "UserDto should not be null");
        assertEquals(id, userDtoResult.getId(), idMessage);
        assertEquals(created, userDtoResult.getCreated(), createdMessage);
        assertEquals(lastUpdated, userDtoResult.getLastUpdated(), lastUpdatedMessage);
        assertEquals(firstName, userDtoResult.getFirstName(), firstNameMessage);
        assertEquals(lastName, userDtoResult.getLastName(), lastNameMessage);
        assertEquals(email, userDtoResult.getEmail(), emailMessage);
        assertEquals(phoneNumber, userDtoResult.getPhoneNumber(), phoneNumberMessage);

        verify(userRepository, times(1)).find(id);
        verify(userMapper, times(1)).map(user);
    }

    @Test
    void testSave() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhoneNumber(phoneNumber);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        User savedUser = new User();
        savedUser.setId(id);
        savedUser.setCreated(created);
        savedUser.setLastUpdated(lastUpdated);
        savedUser.setFirstName(firstName);
        savedUser.setLastName(lastName);
        savedUser.setEmail(email);
        savedUser.setPhoneNumber(phoneNumber);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(id);
        savedUserDto.setCreated(created);
        savedUserDto.setLastUpdated(lastUpdated);
        savedUserDto.setFirstName(firstName);
        savedUserDto.setLastName(lastName);
        savedUserDto.setEmail(email);
        savedUserDto.setPhoneNumber(phoneNumber);

        when(userMapper.map(null, userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.map(savedUser)).thenReturn(savedUserDto);

        // Act
        UserDto userDtoResult = userService.save(userDto);

        // Assert
        assertNotNull(userDtoResult, "UserDtoResult should not be null");
        assertEquals(id, userDtoResult.getId(), idMessage);
        assertEquals(created, userDtoResult.getCreated(), createdMessage);
        assertEquals(lastUpdated, userDtoResult.getLastUpdated(), lastUpdatedMessage);
        assertEquals(firstName, userDtoResult.getFirstName(), firstNameMessage);
        assertEquals(lastName, userDtoResult.getLastName(), lastNameMessage);
        assertEquals(email, userDtoResult.getEmail(), emailMessage);
        assertEquals(phoneNumber, userDtoResult.getPhoneNumber(), phoneNumberMessage);

        verify(userMapper, times(1)).map(null, userDto);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).map(savedUser);
    }

    @Test
    void testUpdate() throws NotFoundException {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhoneNumber(phoneNumber);

        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setCreated(created);
        existingUser.setLastUpdated(lastUpdated);
        existingUser.setFirstName(firstName);
        existingUser.setLastName(lastName);
        existingUser.setEmail(email);
        existingUser.setPhoneNumber(phoneNumber);

        String firstNameUpdated = "John Updated";
        String lastNameUpdated = "Doe Updated";
        String emailUpdated = "updated.john.doe@example.com";
        String phoneNumberUpdated = "+8801888888888";

        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setCreated(created);
        updatedUser.setLastUpdated(lastUpdated);
        updatedUser.setFirstName(firstNameUpdated);
        updatedUser.setLastName(lastNameUpdated);
        updatedUser.setEmail(emailUpdated);
        updatedUser.setPhoneNumber(phoneNumberUpdated);

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(id);
        updatedUserDto.setCreated(created);
        updatedUserDto.setLastUpdated(lastUpdated);
        updatedUserDto.setFirstName(firstNameUpdated);
        updatedUserDto.setLastName(lastNameUpdated);
        updatedUserDto.setEmail(emailUpdated);
        updatedUserDto.setPhoneNumber(phoneNumberUpdated);

        when(userRepository.find(id)).thenReturn(Optional.of(existingUser));
        when(userMapper.map(existingUser, userDto)).thenReturn(updatedUser);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        when(userMapper.map(updatedUser)).thenReturn(updatedUserDto);

        // Act
        UserDto UserDtoResult = userService.update(id, userDto);

        // Assert
        assertNotNull(UserDtoResult, "UserDtoResult should not be null");
        assertEquals(id, UserDtoResult.getId(), id, idMessage);
        assertEquals(created, UserDtoResult.getCreated(), createdMessage);
        assertEquals(lastUpdated, UserDtoResult.getLastUpdated(), lastUpdatedMessage);
        assertEquals(firstNameUpdated, UserDtoResult.getFirstName(), firstNameMessage);
        assertEquals(lastNameUpdated, UserDtoResult.getLastName(), lastNameMessage);
        assertEquals(emailUpdated, UserDtoResult.getEmail(), emailMessage);
        assertEquals(phoneNumberUpdated, UserDtoResult.getPhoneNumber(), phoneNumberMessage);

        verify(userRepository, times(1)).find(id);
        verify(userMapper, times(1)).map(existingUser, userDto);
        verify(userRepository, times(1)).save(updatedUser);
        verify(userMapper, times(1)).map(updatedUser);
    }

    @Test
    void testDelete() throws NotFoundException {
        // Arrange
        User user = new User();
        user.setId(id);
        user.setCreated(created);
        user.setLastUpdated(lastUpdated);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setDeleted(false);

        when(userRepository.find(id)).thenReturn(Optional.of(user));

        // Act
        String response = userService.delete(id, true);

        // Assert
        assertNotNull(response, "Response should not be null");
        assertEquals(Msg.Entity.USER + Msg.Response.DELETE, response);
        assertTrue(user.isDeleted());

        verify(userRepository, times(1)).find(id);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindExceptionUserNotFound() {
        when(userRepository.find(id)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(NotFoundException.class, () -> userService.find(id));

        // Ensure the exception message contains the word "User"
        assertTrue(exception.getMessage().contains("User"), "Exception message should contain 'User'");

        // Verify
        verify(userRepository, times(1)).find(id);
    }
}