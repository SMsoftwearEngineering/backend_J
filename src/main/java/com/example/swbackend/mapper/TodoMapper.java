package com.example.swbackend.mapper;

import com.example.swbackend.DTO.TodoDto;
import com.example.swbackend.domain.TodoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    default TodoDto.TodoResponseDto todoEntityToTodoResponseDto(TodoEntity todoEntity){
        return TodoDto.TodoResponseDto.builder()
                .todoId(todoEntity.getTodoId())
                .completeDate(todoEntity.getCompleteDate())
                .content(todoEntity.getContent())
                .folderId(todoEntity.getFolderEntity().getFolderId())
                .memberId(todoEntity.getMemberEntity().getMemberId())
                .build();
    }





}
