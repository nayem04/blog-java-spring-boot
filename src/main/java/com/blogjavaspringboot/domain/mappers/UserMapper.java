package com.blogjavaspringboot.domain.mappers;

import com.blogjavaspringboot.common.base.BaseMapper;
import com.blogjavaspringboot.domain.dtos.UserDto;
import com.blogjavaspringboot.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDto> {
    @Override
    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreated(user.getCreated());
        userDto.setLastUpdated(user.getLastUpdated());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    @Override
    public User map(User user, UserDto userDto) {
        if (user == null) {
            user = new User();
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
