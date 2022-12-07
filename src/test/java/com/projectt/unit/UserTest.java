package com.projectt.unit;

import com.projectt.domain.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void createUserTest() {
        User user = new User("user1", "1234");

        Assertions.assertThat(user.getUserId()).isEqualTo("user1");
        Assertions.assertThat(user.getPw()).isEqualTo("1234");
    }

    @Test
    void pointTestByArticle() {
        User user = new User("user1", "1234");
        Assertions.assertThat(user.getPoint()).isEqualTo(0);

        user.increasePointByAddArticle();
        Assertions.assertThat(user.getPoint()).isEqualTo(3);

        user.increasePointByAddArticle();
        Assertions.assertThat(user.getPoint()).isEqualTo(6);

        user.decreasePointByAddArticle();
        Assertions.assertThat(user.getPoint()).isEqualTo(3);
    }

    @Test
    void pointTestByComment() {
        User user = new User("user1", "1234");
        Assertions.assertThat(user.getPoint()).isEqualTo(0);

        user.increasePointByAddComments(2);
        Assertions.assertThat(user.getPoint()).isEqualTo(2);

        user.increasePointByAddComments(1);
        Assertions.assertThat(user.getPoint()).isEqualTo(3);

        user.decreasePointByAddComments(1);
        Assertions.assertThat(user.getPoint()).isEqualTo(2);

        user.decreasePointByAddComments(2);
        Assertions.assertThat(user.getPoint()).isEqualTo(0);
    }

    @Test
    void pointTest() {
        User user = new User("user1", "1234");
        Assertions.assertThat(user.getPoint()).isEqualTo(0);

        user.increasePointByAddArticle();
        Assertions.assertThat(user.getPoint()).isEqualTo(3);

        user.increasePointByAddComments(2);
        Assertions.assertThat(user.getPoint()).isEqualTo(5);

        user.decreasePointByAddComments(1);
        Assertions.assertThat(user.getPoint()).isEqualTo(4);

        user.decreasePointByAddComments(2);
        Assertions.assertThat(user.getPoint()).isEqualTo(2);

        user.increasePointByAddArticle();
        Assertions.assertThat(user.getPoint()).isEqualTo(5);

        user.decreasePointByAddArticle();
        Assertions.assertThat(user.getPoint()).isEqualTo(2);
    }
}
