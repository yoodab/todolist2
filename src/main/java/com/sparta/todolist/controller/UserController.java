package com.sparta.todolist.controller;

import com.sparta.todolist.dto.SignupRequestDto;
import com.sparta.todolist.dto.SignupResponseDto;
import com.sparta.todolist.exception.message.Message;
import com.sparta.todolist.exception.message.StatusEnum;
import com.sparta.todolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<Message> signUp(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        SignupResponseDto responseDto = userService.signup(signupRequestDto);
        Message response = Message.createResponse(StatusEnum.OK, "회원가입 성공", responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
