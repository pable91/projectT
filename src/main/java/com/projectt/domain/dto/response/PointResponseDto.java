package com.projectt.domain.dto.response;

import com.projectt.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PointResponseDto {

    private String userId;
    private int point;

    public PointResponseDto(User currentUser) {
        this.userId = currentUser.getUserId();
        this.point = currentUser.getPoint();
    }
}
