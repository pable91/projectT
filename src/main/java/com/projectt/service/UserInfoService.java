package com.projectt.service;

import com.projectt.domain.model.User;
import com.projectt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userid)
                .orElseThrow(() -> new RuntimeException("없는 사용자입니다"));

        return user;
    }
}
