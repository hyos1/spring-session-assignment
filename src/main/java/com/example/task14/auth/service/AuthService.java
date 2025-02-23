package com.example.task14.auth.service;

import com.example.task14.auth.dto.request.AuthLoginRequestDto;
import com.example.task14.auth.dto.request.AuthSignupRequestDto;
import com.example.task14.auth.dto.response.AuthLoginResponseDto;
import com.example.task14.member.dto.response.MemberSaveResponseDto;
import com.example.task14.member.entity.Member;
import com.example.task14.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);

    }

    @Transactional
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new IllegalStateException("해당 이메일 없음")
        );
        return new AuthLoginResponseDto(member.getId());

    }
}
