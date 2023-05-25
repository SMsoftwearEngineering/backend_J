package com.example.swbackend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


public class MemberDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema

    public static class LoginDto{
        @NotEmpty(message = "please fill email")
        @Size(min = 3, max = 30)
        String email;

        @NotEmpty(message = "please fill password")
        @Size(min = 3, max = 30)
        String password;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema

    public static class RegisterDto{
        @NotEmpty(message = "please fill email")
        @Size(min = 3, max = 30)
        String email;

        @NotEmpty(message = "please fill password")
        @Size(min = 3, max = 30)
        String password;

        @NotEmpty(message = "please fill name")
        @Size(min = 2, max = 15)
        String name;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema

    public static class UpdateMemberDto{
        Long memberId;
        @NotEmpty
        @Size(min = 3, max = 30)
        String email;
        @NotEmpty
        @Size(min = 3, max = 30)
        String password;
        @NotEmpty
        @Size(min = 2, max = 15)
        String name;
    }




    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema

    public static class MemberResponseDto{
        Long memberId;
        String email;
        String name;

        @Override
        public String toString() {
            return "memberId: "+memberId + " email: " + email + " name" + name;
        }
    }



    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema

    public static class LoginResponseDto{
        Long memberId;
        String email;
        String name;

        String token;

        @Override
        public String toString() {
            return "memberId: "+memberId + " email: " + email + " name" + name;
        }
    }


}
