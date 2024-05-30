package com.sparta.todolist.entity;

import com.sparta.todolist.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(SignupRequestDto requestDto,UserRoleEnum role) {
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = role;
    }
}
