package com.blogjavaspringboot.domain.mappers;

import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.domain.dtos.PostDto;
import com.blogjavaspringboot.domain.entities.Post;
import com.blogjavaspringboot.domain.entities.User;
import com.blogjavaspringboot.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostMapperTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostMapper postMapper;

    //@ExtendWith(MockitoExtension.class) serve similar purposes in a Mockito setup for unit tests.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapPostToPostDto() {
        // Arrange
        User user = new User();
        user.setId(1L);

        Post post = new Post();
        post.setId(1L);
        post.setCreated(new Date());
        post.setLastUpdated(new Date());
        post.setTitle("Sample Title");
        post.setContent("Sample content with sufficient length to pass validation.");
        post.setUser(user);

        // Act
        PostDto postDto = postMapper.map(post);

        // Assert
        assertNotNull(postDto, "PostDto should not be null");
        assertEquals(post.getId(), postDto.getId(), "ID should match");
        assertEquals(post.getCreated(), postDto.getCreated(), "Created date should match");
        assertEquals(post.getLastUpdated(), postDto.getLastUpdated(), "Last updated date should match");
        assertEquals(post.getTitle(), postDto.getTitle(), "Title should match");
        assertEquals(post.getContent(), postDto.getContent(), "Content should match");
        assertEquals(post.getUser().getId(), postDto.getUserId(), "User ID should match");
    }

    @Test
    void testMapPostDtoToPost() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        PostDto postDto = new PostDto();
        postDto.setTitle("Sample Title");
        postDto.setContent("Sample content with sufficient length to pass validation.");
        postDto.setUserId(userId);

        // Mock the UserRepository behavior to return a user when the user ID is 1
        when(userRepository.find(userId)).thenReturn(Optional.of(user));

        Post post = null;

        // Act
        try {
            post = postMapper.map(null, postDto);
        } catch (NotFoundException e) {
            // Validate the exception message or any other assertions
            assertEquals("User not found with ID: 1", e.getMessage());
        }

        // Assert
        assertNotNull(post, "Post should not be null");
        assertEquals(postDto.getTitle(), post.getTitle(), "Title should match");
        assertEquals(postDto.getContent(), post.getContent(), "Content should match");
        assertEquals(user, post.getUser(), "User should match");

        /*It ensures that during the execution of the map method,
        the userRepository.find() method was called once
        with the user ID provided in the postDto (postDto.getUserId()).
        If the find method is not called exactly once,
        the test will fail, indicating a problem in the code behavior.*/
        // Verify the interactions with the userRepository
        verify(userRepository, times(1)).find(userId);
    }

    @Test
    void testMapPostDtoToPostUserNotFound() {
        // Arrange
        Long userId = 1L;
        PostDto postDto = new PostDto();
        postDto.setTitle("Sample Title");
        postDto.setContent("Sample content with sufficient length to pass validation.");
        postDto.setUserId(userId);

        when(userRepository.find(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(NotFoundException.class, () -> postMapper.map(null, postDto));

        // Ensure the exception message contains the word "User"
        assertTrue(exception.getMessage().contains("User"), "Exception message should contain 'User'");

        // Verify
        verify(userRepository, times(1)).find(userId);
    }
}