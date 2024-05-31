package com.sparta.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String nickname;
    @NotBlank(message = "username은 필수입니다.")
    @Size(min = 4, max = 10, message = "Username은 최소 4자 이상, 10자 이하여야합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "Username은 알파벳 소문자(a~z), 숫자(0~9)로 구성되야합니다.")
    private String username;

    @Size(min = 8, max = 15, message = "password는 최소 8자 이상, 15자 이하여야합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "password는 알파벳 소문자(a~z), 숫자(0~9)로 구성되야합니다.")
    @NotBlank(message = "password는 필수입니다.")
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
