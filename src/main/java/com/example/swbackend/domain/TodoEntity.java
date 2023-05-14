package com.example.swbackend.domain;

import com.example.swbackend.DTO.TodoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long todoId;

    @Column
    String title;

    String content;

    LocalDateTime completeDate;

    boolean done;
    int priority;

    LocalDateTime wishCompleteDate;
    @ManyToOne
    FolderEntity folderEntity;
    @ManyToOne
    MemberEntity memberEntity;


    public static TodoEntity createTodoEntity(String title,
                                              String content,
                                              LocalDateTime completeDate,
                                              int priority,
                                              LocalDateTime wishCompleteDate,
                                              FolderEntity folderEntity,
                                              MemberEntity memberEntity) {
        return TodoEntity.builder()
                .memberEntity(memberEntity)
                .completeDate(completeDate)
                .wishCompleteDate(wishCompleteDate)
                .folderEntity(folderEntity)
                .priority(priority)
                .title(title)
                .content(content)
                .build();
    }


    public void updateTodo(TodoDto.TodoUpdateDto todoUpdateDto,
                                           FolderEntity folderEntity){
        this.title = todoUpdateDto.getTitle();
        this.content = todoUpdateDto.getContent();
        this.completeDate = todoUpdateDto.getCompleteDate();
        this.priority = todoUpdateDto.getPriority();
        this.wishCompleteDate = todoUpdateDto.getWishCompleteDate();
        this.folderEntity = folderEntity;
    }

    public void doneTodo(boolean done){
        this.done = done;
    }

    public void deleteTodo(){

        this.folderEntity = null;
        this.memberEntity = null;

    }
}
