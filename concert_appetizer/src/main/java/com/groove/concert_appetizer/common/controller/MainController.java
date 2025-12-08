package com.groove.concert_appetizer.common.controller;

import com.groove.concert_appetizer.common.response.ApiResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController; // 누락된 어노테이션 추가

import java.time.LocalDateTime;
import java.util.List; // List import 추가

@RestController // Controller 등록을 위해 필수
public class MainController {

    @GetMapping("/api/main") // 프론트엔드가 호출하기 편하게 경로 명시 (/api prefix 권장)
    public ApiResponse<List<PostResponse>> getMainPosts() {
        List<PostResponse> mockList = List.of(
                PostResponse.builder()
                        .postId(1L)
                        .title("2025 아이유 월드투어 [H.E.R]")
                        .artistName("아이유")
                        .concertDate(LocalDateTime.parse("2025-03-15T19:00:00"))
                        .ticketDate(LocalDateTime.parse("2025-02-20T20:00:00"))
                        .imageUrl("https://search.pstatic.net/common?type=o&size=150x200&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F202302%2F20230217150125585.jpg") // 이미지 URL 추가 (UI 퀄리티업)
                        .likeCount(15023)
                        .build(),
                PostResponse.builder()
                        .postId(2L)
                        .title("SEVENTEEN TOUR 'FOLLOW' AGAIN")
                        .artistName("세븐틴")
                        .concertDate(LocalDateTime.parse("2025-04-27T18:00:00"))
                        .ticketDate(LocalDateTime.parse("2025-03-10T20:00:00"))
                        .imageUrl("https://search.pstatic.net/common?type=o&size=150x200&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F202304%2F2023042418042469.jpg") // 이미지 URL 추가
                        .likeCount(9870)
                        .build()
        );
        return ApiResponse.success(mockList);
    }
}

// 간단하게 사용하기 위한 DTO (별도 파일로 분리해도 되지만, 급할 땐 여기에)
@Getter
@Builder
class PostResponse {
    private Long postId;
    private String title;
    private String artistName;
    private LocalDateTime concertDate;
    private LocalDateTime ticketDate;
    private String imageUrl; // 프론트엔드가 포스터 띄울 때 필요
    private int likeCount;
}
