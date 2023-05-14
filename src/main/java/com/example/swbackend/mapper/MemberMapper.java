package com.example.swbackend.mapper;

import com.example.swbackend.DTO.MemberDto;
import com.example.swbackend.domain.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    default MemberEntity registerDtoToMemberEntity(MemberDto.RegisterDto registerDto){
        return   MemberEntity.builder()
                .name(registerDto.getName())
                .password(registerDto.getPassword())
                .email(registerDto.getEmail())
                .build();

    }

    default MemberDto.MemberResponseDto memberEntityToResponseDto(MemberEntity memberEntity){
        return MemberDto.MemberResponseDto.builder()
                .memberId(memberEntity.getMemberId())
                .email(memberEntity.getEmail())
                .name(memberEntity.getName())
                .build();
    }




}
