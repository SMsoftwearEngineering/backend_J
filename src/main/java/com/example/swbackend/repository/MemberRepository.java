package com.example.swbackend.repository;

import com.example.swbackend.DTO.MemberDto;
import com.example.swbackend.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
