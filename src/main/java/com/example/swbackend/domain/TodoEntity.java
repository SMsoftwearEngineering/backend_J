package com.example.swbackend.domain;

import com.example.swbackend.DTO.TodoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long todoId;

    @Column
    String title;

    String content;

    LocalDate completeDate;//실제 완료한 날짜

    boolean done;
    int priority;

    LocalDate wishCompleteDate;
    @ManyToOne(fetch = FetchType.LAZY)
    FolderEntity folderEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    MemberEntity memberEntity;


    public static TodoEntity createTodoEntity(String title,
                                              String content,
                                              int priority,
                                              LocalDate wishCompleteDate,
                                              FolderEntity folderEntity,
                                              MemberEntity memberEntity) {
        return TodoEntity.builder()
                .memberEntity(memberEntity)
                .wishCompleteDate(wishCompleteDate)
                .folderEntity(folderEntity)
                .priority(priority)
                .title(title)
                .content(content)
                .build();
    }


    public void updateTodo(TodoDto.TodoUpdateDto todoUpdateDto,
                           FolderEntity folderEntity) {
        this.title = todoUpdateDto.getTitle();
        this.content = todoUpdateDto.getContent();
        this.priority = todoUpdateDto.getPriority();
        this.wishCompleteDate = todoUpdateDto.getWishCompleteDate();
        this.folderEntity = folderEntity;
    }

    public void doneTodo(boolean done) {
        this.done = done;
    }

    public void deleteTodo() {

        this.folderEntity = null;
        this.memberEntity = null;

    }

    @PreUpdate
    public void onPreUpdate() {
        if(done){
            this.completeDate = LocalDate.now();
        }
    }

}
