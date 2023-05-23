package com.example.swbackend.domain;

import com.example.swbackend.DTO.FolderDto;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.example.swbackend.constant.Color;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long folderId;

    @Column
    @Enumerated(EnumType.STRING)
    Color color;

    String folderTitle;

    @ManyToOne
    MemberEntity memberEntity;

    @OneToMany(mappedBy = "folderEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<TodoEntity> todoEntities  = new ArrayList<>();
    @Transactional
    public void deleteFolder(){
        for(TodoEntity todoEntity : todoEntities){
            todoEntity.deleteTodo();
        }
        this.todoEntities.clear();
        this.memberEntity = null;
    }

     public static FolderEntity createFolder(MemberEntity memberEntity, String folderTitle, Color color){
        return FolderEntity.builder()
                .memberEntity(memberEntity)
                .todoEntities(new ArrayList<>())
                .folderTitle(folderTitle)
                .color(color)
                .build();
     }

    public void addTodoEntity(TodoEntity todoEntity){
        this.todoEntities.add(todoEntity );
    }

    public void updateFolder(FolderDto.UpdateFolderDto updateFolderDto){
        this.folderId = updateFolderDto.getFolderId();
        this.folderTitle = updateFolderDto.getFolderTitle();
        this.color = Color.valueOf(updateFolderDto.getColor());

    }

}
