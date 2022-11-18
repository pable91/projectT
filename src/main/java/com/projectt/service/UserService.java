package com.projectt.service;

import com.projectt.domain.User;
import com.projectt.dto.UserDto;
import com.projectt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(UserDto userDto) {
        String userId = userDto.getUserid();

        Optional<User> foundUser = userRepository.findByUserId(userId);
        if(foundUser.isPresent()) {
            // TODO
            throw new RuntimeException("이미 존재함");
        }

        String pw = passwordEncoder.encode(userDto.getPw());
        User user = new User(userId, pw);
        userRepository.save(user);
        return user;
    }
}
