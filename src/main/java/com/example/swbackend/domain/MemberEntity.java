package com.example.swbackend.domain;

import com.example.swbackend.DTO.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberId;

    String email;

    String name;

    String password;
    @OneToMany(mappedBy = "memberEntity",fetch = FetchType.LAZY)
    List<FolderEntity> folderEntities = new ArrayList<>();
    @OneToMany(mappedBy = "memberEntity",fetch = FetchType.LAZY)
    List<TodoEntity> todoEntities = new ArrayList<>();

    String refreshToken;

    public void deleteMember(){
        this.folderEntities.clear();
        this.todoEntities.clear();
    }

    public void update(MemberDto.UpdateMemberDto updateMemberDto){
        this.name = updateMemberDto.getName();
        this.email = updateMemberDto.getEmail();
        this.password = updateMemberDto.getPassword();
    }

    public void addFolderEntity(FolderEntity folderEntity){
        this.folderEntities.add(folderEntity);
    }

    public void addTodoEntity(TodoEntity todoEntity){
        this.todoEntities.add(todoEntity);
    }

    public void deleteFolderEntity(FolderEntity folderEntity){
        this.folderEntities.remove(folderEntity);
    }

    public void deleteTodoEntity(TodoEntity todo){
        this.todoEntities.remove(todo);
    }



}
