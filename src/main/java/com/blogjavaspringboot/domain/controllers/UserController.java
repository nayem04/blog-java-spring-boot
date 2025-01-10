package com.blogjavaspringboot.domain.controllers;

import com.blogjavaspringboot.common.base.BaseController;
import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.common.routing.Router;
import com.blogjavaspringboot.domain.dtos.UserDto;
import com.blogjavaspringboot.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements BaseController<UserDto> {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Router.User.GET_USERS)
    @Override
    public ResponseEntity<Page<UserDto>> search(@RequestParam(value = "query", defaultValue = "") String query,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                                @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                @RequestParam(value = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                @RequestParam(value = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(userService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.User.GET_USER)
    @Override
    public ResponseEntity<UserDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.find(id));
    }

    @PostMapping(Router.User.CREATE_USER)
    @Override
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PatchMapping(Router.User.UPDATE_USER)
    @Override
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) throws NotFoundException {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping(Router.User.DELETE_USER)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestParam(value = "soft_delete", defaultValue = "true") Boolean softDelete) throws NotFoundException {
        return ResponseEntity.ok(userService.delete(id, softDelete));
    }
}