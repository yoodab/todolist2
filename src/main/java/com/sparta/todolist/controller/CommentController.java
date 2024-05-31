package com.sparta.todolist.controller;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.exception.message.Message;
import com.sparta.todolist.exception.message.StatusEnum;
import com.sparta.todolist.security.UserDetailsImpl;
import com.sparta.todolist.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comment")
    public ResponseEntity<Message> createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto requestDto,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto responseDto = commentService.createComment(todoId, requestDto,userDetails.getUser());
        Message response = Message.createResponse(StatusEnum.OK, "댓글 작성 성공", responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Message> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto,userDetails.getUser());
        Message response = Message.createResponse(StatusEnum.OK, "댓글 수정 성공", responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/todos/{todoId}/comments/{commentId}")
    public ResponseEntity<Message> deleteComment(@PathVariable Long todoId,
                                                 @PathVariable Long commentId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(todoId, commentId, userDetails.getUser());
        Message response = Message.createResponse(StatusEnum.OK, "댓글 삭제 성공", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
