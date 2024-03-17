package com.example.Examen02.service.impl;

import com.example.Examen02.dto.CreateUserDto;
import com.example.Examen02.dto.UserDto;
import com.example.Examen02.entity.User;
import com.example.Examen02.repo.UserRepo;
import com.example.Examen02.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    @Override
    public CreateUserDto create(CreateUserDto userToCreate) throws RuntimeException{
        User user = User.builder()
                .name(userToCreate.getName())
                .surname(userToCreate.getSurname())
                .build();
        try{
            userRepo.save(user);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return userToCreate;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepo.findAll();

        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : users){
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .build();
            userDtoList.add(userDto);

        }
        return userDtoList;
    }

    public UserDto getById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Users id not found"));
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();

        return userDto;
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
