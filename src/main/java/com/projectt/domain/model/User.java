package com.projectt.domain.model;

import com.projectt.common.ErrorCode;
import com.projectt.common.exception.PointValueException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

    private static final int ZERO = 0;
    private static final int POINT_BY_ADD_ARTICLE = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String pw;
    private int point;

    public User(String userId, String pw) {
        this.userId = userId;
        this.pw = pw;
        this.point = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }

    public void increasePointByAddArticle() {
        point += POINT_BY_ADD_ARTICLE;
    }

    public void decreasePointByAddArticle() {
        point -= POINT_BY_ADD_ARTICLE;
    }

    public void increasePointByAddComments(int point) {
        if (point <= ZERO) {
            throw new PointValueException(ErrorCode.POINT_VALUE_ONLY_POSITIVE);
        }
        this.point += point;
    }

    public void decreasePointByAddComments(int point) {
        if (point <= ZERO) {
            throw new PointValueException(ErrorCode.POINT_VALUE_ONLY_POSITIVE);
        }
        this.point -= point;
    }
}
