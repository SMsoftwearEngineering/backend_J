package com.example.swbackend.controller;

import com.example.swbackend.DTO.MemberDto;
import com.example.swbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody MemberDto.RegisterDto postMemberDto){
        return ResponseEntity.ok().body(memberService.createMember(postMemberDto));
    }

    @GetMapping
    public ResponseEntity readMember(@RequestParam Long memberId){
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @PatchMapping
    public ResponseEntity updateMember(@RequestBody MemberDto.UpdateMemberDto updateMemberDto){
        return ResponseEntity.ok().body(memberService.updateMember(updateMemberDto));
    }

    @DeleteMapping
    public ResponseEntity deleteMember(@RequestParam Long memberId){
        return ResponseEntity.ok().body(memberService.deleteMember(memberId));
    }


}
