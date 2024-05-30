package com.sparta.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String nickname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
