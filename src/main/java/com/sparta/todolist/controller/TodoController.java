package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.exception.message.Message;
import com.sparta.todolist.exception.message.StatusEnum;
import com.sparta.todolist.security.UserDetailsImpl;
import com.sparta.todolist.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;


    @PostMapping("/todos")
    public ResponseEntity<Message> createTodo(@Valid @RequestBody TodoRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto responseDto = todoService.createTodo(requestDto,userDetails.getUser());
        Message response = Message.createResponse(StatusEnum.OK, "일정 작성 성공", responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Message> getTodos(@PathVariable Long id) {
        TodoResponseDto responseDto = new TodoResponseDto(todoService.getTodo(id));
        Message response = Message.createResponse(StatusEnum.OK, "일정 조회 성공", responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<Message> getTodos() {
        List<TodoResponseDto> responseDto = todoService.getTodos();
        Message response = Message.createResponse(StatusEnum.OK, "전체 일정 조회 성공", responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Message> updateTodo(@PathVariable Long id,@Valid @RequestBody TodoRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long updateTodoId = todoService.updateTodo(id, requestDto,userDetails.getUser() );
        Message response = Message.createResponse(StatusEnum.OK, "일정 수정 성공", updateTodoId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Message> deleteTodo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long deleteTodoId = todoService.deleteTodo(id,userDetails.getUser());
        Message response = Message.createResponse(StatusEnum.OK, "일정 삭제 성공", deleteTodoId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
