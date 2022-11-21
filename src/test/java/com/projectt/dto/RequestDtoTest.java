package com.projectt.dto;

import com.projectt.domain.dto.request.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class RequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("request dto 데이터 검증")
    void DtoValidationTest() {
        SignupUserDto signupUserDto = new SignupUserDto("", "content", "kim");
        Set<ConstraintViolation<SignupUserDto>> violations1 = validator.validate(signupUserDto);
        Assertions.assertThat(violations1.size()).isEqualTo(1);

        LoginUserDto loginUserDto = new LoginUserDto("", "");
        Set<ConstraintViolation<LoginUserDto>> violations2 = validator.validate(loginUserDto);
        Assertions.assertThat(violations2.size()).isEqualTo(2);

        AddArticleDto addArticleDto = new AddArticleDto("", "");
        Set<ConstraintViolation<AddArticleDto>> violations3 = validator.validate(addArticleDto);
        Assertions.assertThat(violations3.size()).isEqualTo(2);

        UpdateArticleDto updateArticleDto = new UpdateArticleDto(0L, "","test");
        Set<ConstraintViolation<UpdateArticleDto>> violations4 = validator.validate(updateArticleDto);
        Assertions.assertThat(violations4.size()).isEqualTo(2);

        AddCommentDto addCommentDto = new AddCommentDto(0L, "test");
        Set<ConstraintViolation<AddCommentDto>> violations5 = validator.validate(addCommentDto);
        Assertions.assertThat(violations5.size()).isEqualTo(1);
    }
}
