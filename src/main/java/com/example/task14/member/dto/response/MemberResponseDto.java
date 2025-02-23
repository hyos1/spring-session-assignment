package com.example.task14.member.dto.response;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String email;

    public MemberResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
