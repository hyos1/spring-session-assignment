package com.example.task14.member.dto.response;

import lombok.Getter;

@Getter
public class MemberSaveResponseDto {

    private final Long id;
    private final String email;

    public MemberSaveResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
