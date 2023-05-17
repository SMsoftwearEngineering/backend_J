package com.example.swbackend.DTO;

import jakarta.persistence.Entity;
import lombok.*;


public class MemberDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class RegisterDto{
        String email;
        String password;
        String name;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class LoginDto{
        String email;
        String password;
        String name;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UpdateMemberDto{
        Long memberId;

        String email;
        String password;
        String name;
    }

//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    @Setter
//    public static class DeleteMemberDto{
//
//
//    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MemberResponseDto{
        Long memberId;
        String email;
        String name;

    }


}
