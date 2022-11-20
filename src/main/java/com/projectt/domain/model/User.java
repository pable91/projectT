package com.projectt.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String pw;

    private int point;

//    private List<Article> articleList = new ArrayList<>();

    public User(String userId, String pw) {
        this.userId = userId;
        this.pw = pw;
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
        point += 3;
    }

    public void decreasePointByAddArticle() {
        point -= 3;
    }

    public void increasePointByAddComments(int point) {
        this.point += point;
    }
}
