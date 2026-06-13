package org.alima.security;

import org.alima.dto.UserDto;
import org.alima.model.User;

public class UserMapper {


    public static User toEntity(UserDto userDto) {

        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        return user;
    }
}
