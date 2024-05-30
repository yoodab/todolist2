package com.sparta.todolist.dto;

import com.sparta.todolist.entity.User;
import com.sparta.todolist.entity.UserRoleEnum;
import lombok.Getter;


@Getter
public class SignupResponseDto {
    private final Long id;
    private final String nickname;
    private final String username;
    private final UserRoleEnum role;
    public SignupResponseDto(User saveUser) {
        this.id = saveUser.getId();
        this.nickname = saveUser.getNickname();
        this.username = saveUser.getUsername();
        this.role = saveUser.getRole();
    }
}
