package com.projectt.domain.model;

import com.projectt.domain.dto.request.AddCommentDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    private String contents;

    public Comment() {
    }

    private Comment(String commentContents) {
        this.contents = commentContents;
    }

    public static Comment from(AddCommentDto addCommentDTO) {
        return new Comment(addCommentDTO.getCommentsContents());
    }

    public void setArticle(Article article) {
        if (this.article != null) {
            this.article.getCommentList().remove(this);
        }
        this.article = article;
        article.getCommentList().add(this);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", article=" + article +
                ", contents='" + contents + '\'' +
                '}';
    }
}
