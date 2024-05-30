package com.sparta.todolist.entity;

import com.sparta.todolist.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String user_id;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(CommentRequestDto requestDto, Todo todo) {
        this.content = requestDto.getContent();
        this.user_id = requestDto.getUserId();
        this.todo = todo;
    }
}
