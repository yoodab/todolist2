package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String userId;
    private final Long todoId;
    private final String createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUser_id();
        this.todoId = comment.getTodo().getId();
        this.createdAt = comment.getCreatedAt().toString();
    }
}
