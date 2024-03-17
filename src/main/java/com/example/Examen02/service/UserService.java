package com.example.Examen02.service;



import com.example.Examen02.dto.CreateUserDto;
import com.example.Examen02.dto.UserDto;

import java.util.List;

public interface UserService {
    CreateUserDto create(CreateUserDto userToCreate);
    List<UserDto> getAll();
    UserDto getById(Long id);
    void deleteById(Long id);
}
