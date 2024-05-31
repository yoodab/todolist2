package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {

    private final Long id;

    private final String title;

    private final String content;
    private final String username;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;



    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
        this.modifiedAt = todo.getModifiedAt();
    }
}


