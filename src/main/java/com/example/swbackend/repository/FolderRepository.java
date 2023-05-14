package com.example.swbackend.repository;

import com.example.swbackend.domain.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {


}
