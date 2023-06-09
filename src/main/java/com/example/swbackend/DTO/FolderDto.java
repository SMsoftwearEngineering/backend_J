package com.example.swbackend.DTO;

import com.example.swbackend.constant.Color;
import com.example.swbackend.domain.TodoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


public class FolderDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class FolderResponseDto {
        Long folderId;
        String color;
        String folderTitle;
        Long memberId;
        List<TodoDto.TodoResponseDto> todoResponseDtos = new ArrayList<>();
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Schema
    @Getter
    @Setter
    @Builder
    public static class FolderPostDto {

        String folderTitle;
        String memberId;
        Color color;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Schema
    @Getter
    @Setter
    @Builder
    public static class NewFolderPostDto {

        String folderTitle;
        Long memberId;
        Color color;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Schema
    @Getter
    @Setter
    @Builder
    public static class New2FolderPostDto {

        String folderTitle;
        int memberId;
        Color color;
    }


    public static class FolderDeleteDto {

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema
    @Builder
    public static class UpdateFolderDto {
        Long folderId;
        String Color;
        String folderTitle;
    }

}
