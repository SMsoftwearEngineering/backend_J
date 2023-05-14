package com.example.swbackend.mapper;

import com.example.swbackend.DTO.FolderDto;
import com.example.swbackend.DTO.TodoDto;
import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.TodoEntity;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")

public interface FolderMapper {


    default TodoDto.TodoResponseDto todoEntityToTodoResponseDto(TodoEntity todoEntity){
        return TodoDto.TodoResponseDto.builder()
                .todoId(todoEntity.getTodoId())
                .completeDate(todoEntity.getCompleteDate())
                .content(todoEntity.getContent())
                .folderId(todoEntity.getFolderEntity().getFolderId())
                .memberId(todoEntity.getMemberEntity().getMemberId())
                .build();
    }


    default FolderDto.FolderResponseDto folderEntityToFolderResponseDto(FolderEntity folderEntity){
        return FolderDto.FolderResponseDto.builder()
                .folderId(folderEntity.getFolderId())
                .folderTitle(folderEntity.getFolderTitle())
                .memberId(folderEntity.getMemberEntity().getMemberId())
                .todoResponseDtos(folderEntity.getTodoEntities()
                        .stream()
                        .map(this::todoEntityToTodoResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
