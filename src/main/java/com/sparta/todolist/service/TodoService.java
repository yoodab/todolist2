package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.exception.InvalidPasswordException;
import com.sparta.todolist.exception.TodoNotFoundException;
import com.sparta.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        // RequestDto -> Entity
        Todo todo = new Todo(requestDto,user);

        // DB 저장
        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponseDto(saveTodo);
    }


    public List<TodoResponseDto> getTodos() {
        // DB 조회
        return todoRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(TodoResponseDto::new)
                .toList();
    }

    @Transactional
    public Long updateTodo(Long id, TodoRequestDto requestDto, User user) {

        // 해당 메모가 DB에 존재하는지 확인
        Todo todo = getTodo(id);

        // username 확인
        validateUsername(todo.getUser().getUsername(), user.getUsername());

        // 수정
        todo.update(requestDto);

        return id;
    }

    public Long deleteTodo(Long id, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Todo todo = getTodo(id);

        // username 확인
        validateUsername(todo.getUser().getUsername(), user.getUsername());

        // 삭제
        todoRepository.delete(todo);

        return id;
    }


    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new TodoNotFoundException("일정이 존재하지 않습니다.")
        );
    }

    private void validateUsername(String actualUsername, String enteredUsername) {
        if (!actualUsername.equals(enteredUsername)) {
            throw new InvalidPasswordException("작성자가 일치하지 않습니다.");
        }
    }
}
