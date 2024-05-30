package com.sparta.todolist.controller;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.exception.message.Message;
import com.sparta.todolist.exception.message.StatusEnum;
import com.sparta.todolist.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comment")
    public ResponseEntity<Message> createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.createComment(todoId, requestDto);
        Message response = Message.createResponse(StatusEnum.OK, "댓글 작성 성공", responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
