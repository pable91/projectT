package com.projectt.service;

import com.projectt.domain.User;
import com.projectt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    public User loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userid)
                .orElseThrow(() -> new RuntimeException("없는 사용자입니다"));

        return user;
    }
}
