package com.groove.concert_appetizer.domain.member.controller;

import com.groove.concert_appetizer.common.response.ApiResponse;
import com.groove.concert_appetizer.domain.member.dto.MemberRequest;
import com.groove.concert_appetizer.domain.member.dto.MemberResponse;
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
        MemberResponse response = memberService.login(request);
        // 프론트엔드는 이 response의 memberId를 LocalStorage에 저장해서 쓰면 됩니다.
        return ApiResponse.success(response);
    }
}
