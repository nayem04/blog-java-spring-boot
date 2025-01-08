package com.blogjavaspringboot.domain.services;

import com.blogjavaspringboot.common.base.BaseService;
import com.blogjavaspringboot.common.constants.Msg;
import com.blogjavaspringboot.common.exceptions.NotFoundException;
import com.blogjavaspringboot.common.utilities.ExceptionUtil;
import com.blogjavaspringboot.common.utilities.PageAttribute;
import com.blogjavaspringboot.domain.dtos.UserDto;
import com.blogjavaspringboot.domain.entities.User;
import com.blogjavaspringboot.domain.mappers.UserMapper;
import com.blogjavaspringboot.domain.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BaseService<UserDto> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<User> userPage = (pageableLimit) ?
                userRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                userRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return userPage.map(userMapper::map);
    }

    @Override
    public UserDto find(Long id) throws NotFoundException {
        User user = userRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, id));
        return userMapper.map(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.map(null, userDto);
        user = userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) throws NotFoundException {
        User user = userRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, id));
        user = userMapper.map(user, userDto);
        user = userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    public String delete(Long id, Boolean softDelete) throws NotFoundException {
        User user = userRepository.find(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, id));
        if (softDelete) {
            user.setDeleted(true);
            userRepository.save(user);
        } else {
            userRepository.delete(user);
        }
        return Msg.Entity.USER + Msg.Response.DELETE;
    }
}
