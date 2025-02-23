package com.example.task14.member.service;

import com.example.task14.member.dto.request.MemberUpdateRequestDto;
import com.example.task14.member.dto.response.MemberResponseDto;
import com.example.task14.member.dto.request.MemberSaveRequestDto;
import com.example.task14.member.dto.response.MemberSaveResponseDto;
import com.example.task14.member.entity.Member;
import com.example.task14.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    필요없음
//    @Transactional
//    public MemberSaveResponseDto save(MemberSaveRequestDto dto) {
//        Member member = new Member(dto.getEmail());
//        Member saved = memberRepository.save(member);
//
//        return new MemberSaveResponseDto(saved.getId(), saved.getEmail());
//    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

//        List<MemberResponseDto> dtos = new ArrayList<>();
//
//        for (Member member : members) {
//            dtos.add(new MemberResponseDto(member.getId(), member.getEmail()));
//        }
//        return dtos;

        return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 멤버 없음")
        );
        return new MemberResponseDto(findMember.getId(), findMember.getEmail());
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("그런 멤버 없음")
        );
        findMember.update(dto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
