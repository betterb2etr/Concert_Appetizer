package com.groove.concert_appetizer.domain.member.dto;

import com.groove.concert_appetizer.domain.member.entity.Member;
import com.groove.concert_appetizer.domain.member.entity.MemberRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {
    private Long memberId;
    private String email;
    private String nickname;
    private MemberRole role;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }
}
