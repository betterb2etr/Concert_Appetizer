package com.groove.concert_appetizer.domain.member.controller;

import com.groove.concert_appetizer.common.response.ApiResponse;
import com.groove.concert_appetizer.domain.member.dto.MemberRequest;
import com.groove.concert_appetizer.domain.member.dto.MemberResponse;
import com.groove.concert_appetizer.domain.member.entity.MemberRole;
import com.groove.concert_appetizer.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ApiResponse<Long> join(@RequestBody MemberRequest.Join request) {
        Long memberId = memberService.join(request);
        return ApiResponse.success(memberId);
    }


    @PostMapping("/login")
    public ApiResponse<MemberResponse> login(@RequestBody MemberRequest.Login request) {
        // DB 조회 로직 제거 -> 무조건 성공 처리
        MemberResponse mockMember = MemberResponse.builder()
                .memberId(1L)
                .email(request.getEmail())
                .nickname("해커톤러버") // 아무 닉네임이나 리턴
                .role(MemberRole.GENERAL)
                .build();

        return ApiResponse.success(mockMember);
    }

}
