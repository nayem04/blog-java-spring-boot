package com.blogjavaspringboot.domain.mappers;

import com.blogjavaspringboot.common.base.BaseMapper;
import com.blogjavaspringboot.common.constants.Msg;
import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.common.utilities.ExceptionUtil;
import com.blogjavaspringboot.domain.dtos.PostDto;
import com.blogjavaspringboot.domain.entities.Post;
import com.blogjavaspringboot.domain.entities.User;
import com.blogjavaspringboot.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements BaseMapper<Post, PostDto> {
    private final UserRepository userRepository;

    public PostMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PostDto map(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setCreated(post.getCreated());
        postDto.setLastUpdated(post.getLastUpdated());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }

    @Override
    public Post map(Post post, PostDto postDto) throws NotFoundException {
        User user = userRepository.find(postDto.getUserId()).orElseThrow(
                () -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, postDto.getUserId()));

        if (post == null) {
            post = new Post();
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);
        return post;
    }
}
