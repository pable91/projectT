package com.projectt.domain.model;

import com.projectt.domain.dto.AddArticleDto;
import com.projectt.domain.dto.UpdateArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String contents;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "article")
    private List<Comment> commentList = new ArrayList<>();

    private Article(String articleTitle, String articleContents, User user) {
        this.title = articleTitle;
        this.contents = articleContents;
        this.user = user;
    }

    public Article() {
    }

    public static Article of(AddArticleDto articleDto, User user) {
        return new Article(articleDto.getArticleTitle(), articleDto.getArticleTitle(), user);
    }

    public void update(UpdateArticleDto articleDto) {
        this.title = articleDto.getArticleTitle();
        this.contents = articleDto.getArticleContents();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", user=" + user +
                '}';
    }
}
