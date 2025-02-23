package com.example.task14.member.dto.response;

import lombok.Getter;

@Getter
public class MemberUpdateResponseDto {

    private final Long id;
    private final String email;

    public MemberUpdateResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
