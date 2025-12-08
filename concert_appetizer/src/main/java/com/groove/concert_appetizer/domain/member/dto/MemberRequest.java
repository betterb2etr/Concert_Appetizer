package com.groove.concert_appetizer.domain.member.dto;

import com.groove.concert_appetizer.domain.member.entity.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequest {

    @Getter
    @NoArgsConstructor
    public static class Join {
        private String email;
        private String password;
        private String nickname;
        private MemberRole role; // 테스트 편의상 입력받음 (기본값 GENERAL)
    }

    @Getter
    @NoArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }
}
