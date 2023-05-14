package com.example.swbackend.DTO;

import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoResponseDto{

        Long todoId;

        String title;

        String content;

        LocalDateTime completeDate;

        int priority;

        LocalDateTime wishCompleteDate;

        Long folderId;

        Long  memberId;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoPostDto{

        String title;

        String content;

        LocalDateTime completeDate;

        int priority;

        LocalDateTime wishCompleteDate;

        Long folderId;

        Long  memberId;

    }



    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
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





    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoUpdateDto{

        Long todoId;

        String title;

        String content;

        LocalDateTime completeDate;

        int priority;

        LocalDateTime wishCompleteDate;

        Long folderId;
    }


}
