package com.blogjavaspringboot.domain.services;

import com.blogjavaspringboot.common.base.BaseService;
import com.blogjavaspringboot.common.constants.Msg;
import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.common.utilities.ExceptionUtil;
import com.blogjavaspringboot.common.utilities.PageAttribute;
import com.blogjavaspringboot.domain.dtos.PostDto;
import com.blogjavaspringboot.domain.entities.Post;
import com.blogjavaspringboot.domain.mappers.PostMapper;
import com.blogjavaspringboot.domain.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService implements BaseService<PostDto> {
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public PostService(PostMapper postMapper, PostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    @Override
    public Page<PostDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<Post> postPage = (pageableLimit) ?
                postRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                postRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return postPage.map(postMapper::map);
    }

    @Override
    public PostDto find(Long id) throws NotFoundException {
        Post post = postRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.POST, id));
        return postMapper.map(post);
    }

    @Override
    public PostDto save(PostDto postDto) throws NotFoundException {
        Post post = postMapper.map(null, postDto);
        post = postRepository.save(post);
        return postMapper.map(post);
    }

    @Override
    public PostDto update(Long id, PostDto postDto) throws NotFoundException {
        Post post = postRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.POST, id));
        post = postMapper.map(post, postDto);
        post = postRepository.save(post);
        return postMapper.map(post);
    }

    @Override
    public String delete(Long id, Boolean softDelete) throws NotFoundException {
        Post post = postRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.POST, id));
        if (softDelete) {
            post.setDeleted(true);
            postRepository.save(post);
        } else {
            postRepository.delete(post);
        }
        return Msg.Entity.POST + Msg.Response.DELETE;
    }
}
