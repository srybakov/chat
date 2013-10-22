package com.ibukanov.chat.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class RegisterForm {

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String nickname;

    @Length(min=6)
    private String login;

    @Length(min=6)
    private String password;

    @Email
    @NotNull
    @NotEmpty
    private String email;
}
