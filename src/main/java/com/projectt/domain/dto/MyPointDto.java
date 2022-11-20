package com.projectt.domain.dto;

import com.projectt.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyPointDto {

    private String userId;
    private int point;

    public static MyPointDto from(User user) {
        return new MyPointDto(user.getUserId(), user.getPoint());
    }
}
