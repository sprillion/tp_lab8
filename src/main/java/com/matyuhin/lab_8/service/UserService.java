package com.matyuhin.lab_8.service;

import com.matyuhin.lab_8.dto.UserDto;
import com.matyuhin.lab_8.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
