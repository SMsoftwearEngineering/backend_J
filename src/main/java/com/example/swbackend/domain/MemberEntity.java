package com.example.swbackend.domain;

import com.example.swbackend.DTO.MemberDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "아이디 중복 불가", columnNames = "email")
})
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberId;
    @Column(name = "email")
    String email;
    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @OneToMany(mappedBy = "memberEntity",fetch = FetchType.LAZY)
    List<FolderEntity> folderEntities = new ArrayList<>();
    @OneToMany(mappedBy = "memberEntity",fetch = FetchType.LAZY)
    List<TodoEntity> todoEntities = new ArrayList<>();

    String refreshToken;

    @Transactional
    public void deleteMember(){
        for(FolderEntity folderEntity: folderEntities){
            folderEntity.deleteFolder();
        }

        for(TodoEntity todo: todoEntities){
            todo.deleteTodo();
        }
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
