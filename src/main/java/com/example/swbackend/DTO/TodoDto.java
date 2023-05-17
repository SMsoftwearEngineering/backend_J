package com.example.swbackend.DTO;

import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class TodoDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class TodoResponseDto{

        Long todoId;

        String title;

        String content;

        LocalDate completeDate;

        int priority;

        LocalDate wishCompleteDate;

        Long folderId;

        Long  memberId;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class TodoPostDto{

        String title;

        String content;

        int priority;

        LocalDate wishCompleteDate;

        Long folderId;

        Long  memberId;

    }




    public static class TodoDeleteDto{

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DoneDto{
        Long todoId;
        boolean done;
    }




    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class TodoUpdateDto{

        Long todoId;

        String title;

        String content;

        int priority;

        LocalDate wishCompleteDate;

        Long folderId;
    }


}
