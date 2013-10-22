package com.ibukanov.chat.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@ToString
@Getter
@Setter
public class UserForm {

    @NotEmpty
    private String nickname;

    @Length(min=6)
    private String password;
}
