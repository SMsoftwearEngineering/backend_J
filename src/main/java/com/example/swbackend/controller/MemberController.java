package com.example.swbackend.controller;

import com.example.swbackend.DTO.MemberDto;
import com.example.swbackend.domain.MemberEntity;
import com.example.swbackend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/auth/register")
    @Operation(summary = "맴버등록", description = "맴버등록")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "맴버가 정상적으로 등록",
            content = @Content(schema = @Schema(implementation = MemberDto.MemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity register(@RequestBody @Valid MemberDto.RegisterDto postMemberDto){
        return ResponseEntity.ok().body(memberService.createMember(postMemberDto));
    }


    @PostMapping("/auth/login")
    @Operation(summary = "로그인", description = "로그인")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "맴버가 정상적으로 등록",
            content = @Content(schema = @Schema(implementation = MemberDto.LoginResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity<?> authenticate(@RequestBody @Valid MemberDto.LoginDto loginDto) {
        return ResponseEntity.ok().body(memberService.authenticate(loginDto));
    }




    @GetMapping
    public ResponseEntity readMember(@RequestParam Long memberId){
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @PatchMapping
    public ResponseEntity updateMember(@RequestBody @Valid MemberDto.UpdateMemberDto updateMemberDto){
        return ResponseEntity.ok().body(memberService.updateMember(updateMemberDto));
    }

    @DeleteMapping
    public ResponseEntity deleteMember(@RequestParam Long memberId){
        return ResponseEntity.ok().body(memberService.deleteMember(memberId));
    }


}
