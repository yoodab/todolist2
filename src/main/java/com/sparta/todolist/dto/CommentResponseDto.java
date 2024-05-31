package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.User;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String username;
    private final Long todoId;
    private final String createdAt;

    public CommentResponseDto(Comment comment, User user) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = user.getUsername();
        this.todoId = comment.getTodo().getId();
        this.createdAt = comment.getCreatedAt().toString();
    }
}
