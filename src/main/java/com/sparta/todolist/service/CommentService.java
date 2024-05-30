package com.sparta.todolist.service;


import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.exception.InvalidPasswordException;
import com.sparta.todolist.exception.TodoNotFoundException;
import com.sparta.todolist.repository.CommentRepository;
import com.sparta.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;


    @Transactional
    public CommentResponseDto createComment(Long todoId, CommentRequestDto requestDto) {
        // 예외 처리
        if (todoId == null) {
            throw new IllegalArgumentException("일정 ID가 입력되지 않았습니다.");
        }

        if (requestDto.getContent() == null || requestDto.getContent().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 비어 있습니다.");
        }
        if (requestDto.getUserId() == null || requestDto.getUserId().isEmpty()) {
            throw new IllegalArgumentException("사용자 ID가 비어 있습니다.");
        }

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException("일정이 존재하지 않습니다."));


        Comment savedComment = commentRepository.save(new Comment(requestDto,todo));

        return new CommentResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        if (commentId == null) {
            throw new IllegalArgumentException("댓글 ID가 입력되지 않았습니다.");
        }

        if (requestDto.getContent() == null || requestDto.getContent().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 비어 있습니다.");
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new TodoNotFoundException("댓글이 존재하지 않습니다."));

        if (!comment.getUser_id().equals(requestDto.getUserId())) {
            throw new InvalidPasswordException("댓글 작성자만 수정할 수 있습니다.");
        }

        // 댓글 수정
        comment.setContent(requestDto.getContent());

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Long todoId, Long commentId, String currentUserId) {
        if (todoId == null || commentId == null) {
            throw new IllegalArgumentException("일정 ID 또는 댓글 ID가 입력되지 않았습니다.");
        }

        todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException("일정이 존재하지 않습니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new TodoNotFoundException("댓글이 존재하지 않습니다."));

        if (!comment.getUser_id().equals(currentUserId)) {
            throw new InvalidPasswordException("댓글의 작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }
}
