package com.example.swbackend.service;

import com.example.swbackend.DTO.FolderDto;
import com.example.swbackend.DTO.TodoDto;
import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.MemberEntity;
import com.example.swbackend.domain.TodoEntity;
import com.example.swbackend.mapper.TodoMapper;
import com.example.swbackend.repository.FolderRepository;
import com.example.swbackend.repository.MemberRepository;
import com.example.swbackend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final MemberRepository memberRepository;
    private final FolderRepository folderRepository;

    @Transactional(readOnly = true)
    public TodoDto.TodoResponseDto readTodo(Long todoId){

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(()->new RuntimeException("there is no todo"));
        return todoMapper.todoEntityToTodoResponseDto(todoEntity);
    }

    @Transactional
    public TodoDto.TodoResponseDto createTodo(TodoDto.TodoPostDto todoPostDto){

        MemberEntity memberEntity = memberRepository.findById(todoPostDto.getMemberId())
                .orElseThrow(()->new RuntimeException("there is no kind of member"));

        FolderEntity folderEntity = folderRepository.findById(todoPostDto.getFolderId())
                .orElseThrow(()-> new RuntimeException(" there is no kind of folder"));

        TodoEntity todoEntity = TodoEntity.createTodoEntity(
                todoPostDto.getTitle(),
                todoPostDto.getContent(),
                todoPostDto.getCompleteDate(),
                todoPostDto.getPriority(),
                todoPostDto.getWishCompleteDate(),
                folderEntity,
                memberEntity
        );

        TodoEntity savedTodoEntity = todoRepository.save(todoEntity);
        memberEntity.addTodoEntity(todoEntity);
        folderEntity.addTodoEntity(todoEntity);

        return todoMapper.todoEntityToTodoResponseDto(savedTodoEntity);
    }

    @Transactional
    public TodoDto.TodoResponseDto updateTodoDto(TodoDto.TodoUpdateDto todoUpdateDto){
        TodoEntity todoEntity = todoRepository.findById(todoUpdateDto.getTodoId())
                .orElseThrow(()->new RuntimeException("there is no todo"));

        FolderEntity folderEntity = folderRepository.findById(todoUpdateDto.getFolderId())
                .orElseThrow(()-> new RuntimeException("there is no folder"));


        todoEntity.updateTodo(todoUpdateDto,folderEntity);
        return todoMapper.todoEntityToTodoResponseDto(todoEntity);
    }


    @Transactional
    public TodoDto.TodoResponseDto updateDoneDto(TodoDto.DoneDto doneDto){
        TodoEntity todoEntity = todoRepository.findById(doneDto.getTodoId())
                .orElseThrow(()->new RuntimeException("there is no todo"));
        todoEntity.doneTodo(doneDto.isDone());
        return todoMapper.todoEntityToTodoResponseDto(todoEntity);
    }


    @Transactional
    public String deleteTodo(Long todoId){
        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(()->new RuntimeException("there is no todo"));

        todoEntity.deleteTodo();
        todoRepository.delete(todoEntity);

        return "todo deleted : " + todoId;
    }


}
