package com.example.task14.todo.service;

import com.example.task14.member.entity.Member;
import com.example.task14.member.repository.MemberRepository;
import com.example.task14.todo.dto.request.TodoSaveRequestDto;
import com.example.task14.todo.dto.request.TodoUpdateRequestDto;
import com.example.task14.todo.dto.response.TodoResponseDto;
import com.example.task14.todo.dto.response.TodoSaveResponseDto;
import com.example.task14.todo.dto.response.TodoUpdateResponseDto;
import com.example.task14.todo.entity.Todo;
import com.example.task14.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 멤버 없음요")
        );
        Todo todo = new Todo(dto.getContent(), member);
        Todo saved = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                saved.getId(),
                saved.getContent(),
                member.getId(),
                member.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDto> dtos = new ArrayList<>();

        for (Todo todo : todos) {
            dtos.add(new TodoResponseDto(todo.getId(), todo.getContent()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("그런 일정 없습니다~")
        );
        return new TodoResponseDto(findTodo.getId(), findTodo.getContent());
    }

    @Transactional
    public TodoUpdateResponseDto update(Long memberId, Long todoId, TodoUpdateRequestDto dto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 멤버 없음요")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("그런 일정 없습니다~")
        );

        if (member.getId().equals(todo.getMember().getId())) {
            throw new IllegalStateException("Todo 작성자가 아님~!!!");
        }

        todo.update(dto.getContent());

        return new TodoUpdateResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional
    public void deleteById(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 멤버 없음요")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("그런 일정 없습니다~")
        );

        if (member.getId().equals(todo.getMember().getId())) {
            throw new IllegalStateException("Todo 작성자가 아님~!!!");
        }

        todoRepository.deleteById(todoId);
    }
}
