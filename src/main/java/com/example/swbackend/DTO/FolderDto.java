package com.example.swbackend.DTO;

import com.example.swbackend.domain.TodoEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolderDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FolderResponseDto {
        Long folderId;
        String color;
        String folderTitle;
        Long memberId;
        List<TodoDto.TodoResponseDto> todoResponseDtos = new ArrayList<>();
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FolderPostDto {

        String folderTitle;
        Long memberId;
        String Color;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FolderDeleteDto {

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateFolderDto {
        Long folderId;
        String Color;
        String folderTitle;
    }

}
