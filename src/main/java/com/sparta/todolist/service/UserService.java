package com.sparta.todolist.service;

import com.sparta.todolist.dto.SignupRequestDto;
import com.sparta.todolist.dto.SignupResponseDto;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.entity.UserRoleEnum;
import com.sparta.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }


        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(requestDto,role);
        User saveUser = userRepository.save(user);

        return new SignupResponseDto(saveUser);
    }

}
