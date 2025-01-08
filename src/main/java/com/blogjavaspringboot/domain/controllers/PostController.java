package com.blogjavaspringboot.domain.controllers;

import com.blogjavaspringboot.common.base.BaseController;
import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.common.routing.Router;
import com.blogjavaspringboot.domain.dtos.PostDto;
import com.blogjavaspringboot.domain.services.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController implements BaseController<PostDto> {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(Router.Post.GET_POSTS)
    @Override
    public ResponseEntity<Page<PostDto>> search(@RequestParam(value = "query", defaultValue = "") String query,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                                @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                @RequestParam(value = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                @RequestParam(value = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(service.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.Post.GET_POST)
    @Override
    public ResponseEntity<PostDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(service.find(id));
    }

    @PostMapping(Router.Post.CREATE_POST)
    @Override
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto postDto) throws NotFoundException {
        return ResponseEntity.ok(service.save(postDto));
    }

    @PatchMapping(Router.Post.UPDATE_POST)
    @Override
    public ResponseEntity<PostDto> update(@PathVariable Long id, @Valid @RequestBody PostDto postDto) throws NotFoundException {
        return ResponseEntity.ok(service.update(id, postDto));
    }

    @DeleteMapping(Router.Post.DELETE_POST)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestParam(value = "soft_delete", defaultValue = "true") Boolean softDelete) throws NotFoundException {
        return ResponseEntity.ok(service.delete(id, softDelete));
    }
}
