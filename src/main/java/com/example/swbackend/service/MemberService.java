package com.example.swbackend.service;

import com.example.swbackend.DTO.MemberDto;
import com.example.swbackend.domain.MemberEntity;
import com.example.swbackend.mapper.MemberMapper;
import com.example.swbackend.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    @Transactional
    public MemberDto.MemberResponseDto createMember(MemberDto.RegisterDto registerDto){
        MemberEntity memberEntity = memberMapper.registerDtoToMemberEntity(registerDto);
        MemberEntity savedMemberEntity = memberRepository.save(memberEntity);
        return memberMapper.memberEntityToResponseDto(memberEntity);
    }

    @Transactional
    public String deleteMember(Long memberId){

        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("there's no member!!"));
        memberEntity.deleteMember();/*
        폴더 내부에 존재하는 맴버 삭제
        tod 에 들어있는거 삭제
        */
        memberRepository.deleteById(memberId);

        return "deleted memberId : "+memberId;
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberResponseDto getMember(Long memberId){
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(()->new RuntimeException("there is no member"));

        return memberMapper.memberEntityToResponseDto(memberEntity);
    }

    @Transactional
    public MemberEntity readMember(Long memberId){
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(()->new RuntimeException("there is no member"));

        return memberEntity;
    }


    @Transactional
    public MemberDto.MemberResponseDto updateMember(MemberDto.UpdateMemberDto updateMemberDto){
        MemberEntity  memberEntity = memberRepository.findById(updateMemberDto.getMemberId())
                .orElseThrow(()-> new RuntimeException("there is no Member"));
        memberEntity.update(updateMemberDto);
        return memberMapper.memberEntityToResponseDto(memberEntity);
    }


}
