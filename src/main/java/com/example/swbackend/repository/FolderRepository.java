package com.example.swbackend.repository;

import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {

    Page<FolderEntity> findAllByMemberEntity(MemberEntity memberEntity, Pageable pageable);


}
