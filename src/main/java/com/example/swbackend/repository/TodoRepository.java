package com.example.swbackend.repository;

import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    Page<TodoEntity> findAllByFolderEntity(FolderEntity folderEntity, Pageable pageable);
}
