package com.groove.concert_appetizer.domain.member.service;

import com.groove.concert_appetizer.domain.member.dto.MemberRequest;
import com.groove.concert_appetizer.domain.member.dto.MemberResponse;
import com.groove.concert_appetizer.domain.member.entity.Member;
import com.groove.concert_appetizer.domain.member.entity.MemberRole;
import com.groove.concert_appetizer.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberRequest.Join request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 암호화 없이 저장 (해커톤용 간소화)
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .role(request.getRole() == null ? MemberRole.GENERAL : request.getRole())
                .build();

        return memberRepository.save(member).getId();
    }

    public MemberResponse login(MemberRequest.Login request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return MemberResponse.from(member);
    }
}
