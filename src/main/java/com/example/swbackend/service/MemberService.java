package com.example.swbackend.service;

import com.example.swbackend.DTO.MemberDto;
import com.example.swbackend.config.JwtService;
import com.example.swbackend.domain.MemberEntity;
import com.example.swbackend.mapper.MemberMapper;
import com.example.swbackend.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;
    private  final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Transactional
    public MemberDto.MemberResponseDto createMember(MemberDto.RegisterDto registerDto){
        MemberEntity memberEntity = memberMapper.registerDtoToMemberEntity(registerDto);
        memberEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        MemberEntity savedMemberEntity = memberRepository.save(memberEntity);
        return memberMapper.memberEntityToResponseDto(memberEntity);
    }

    public MemberDto.LoginResponseDto authenticate(MemberDto.LoginDto loginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        var user = memberRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("no user")
        );
        var jwtToken = jwtService.generateToken(user);
        return MemberDto.LoginResponseDto.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .memberId(user.getMemberId())
                .build();
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

    @Transactional(readOnly = true)
    public MemberDto.MemberResponseDto findByUserCredential(Principal principal){
        MemberEntity memberEntity = memberRepository.findByEmail(principal.getName()).orElseThrow(
                ()-> new RuntimeException("there is no member")
        );

        return memberMapper.memberEntityToResponseDto(memberEntity);
    }


    public MemberEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final MemberEntity originalUser = memberRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("there is no kind of user")
        );

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }

}
