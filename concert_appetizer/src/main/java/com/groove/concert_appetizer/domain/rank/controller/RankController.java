package com.groove.concert_appetizer.domain.rank.controller;

import com.groove.concert_appetizer.common.response.ApiResponse;
import com.groove.concert_appetizer.domain.rank.dto.RankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ranks")
public class RankController {

    private final RankService rankService;

    /**
     * GET /api/v1/ranks/overall
     * 전체 서비스의 명예의 전당 Top N 리스트를 조회합니다.
     * @param limit 조회할 랭킹 개수 (기본값: 10)
     * @return 랭킹 리스트 (ApiResponse로 래핑)
     */
    @GetMapping("/overall")
    public ResponseEntity<ApiResponse<List<RankResponse>>> getOverallTopRanks(
            @RequestParam(defaultValue = "10") int limit) {

        List<RankResponse> ranks = rankService.getOverallTopRanks(limit);

        return ResponseEntity.ok(ApiResponse.success(ranks, "전체 랭킹 Top " + limit + " 조회 성공"));
    }

    /**
     * GET /api/v1/ranks/concert
     * 특정 공연의 예상 플리 랭킹 Top N 리스트를 조회합니다.
     * @param postId 대상 공연 게시글 ID (필수)
     * @param limit 조회할 랭킹 개수 (기본값: 5)
     * @return 랭킹 리스트 (ApiResponse로 래핑)
     */
    @GetMapping("/concert")
    public ResponseEntity<ApiResponse<List<RankResponse>>> getConcertTopRanks(
            @RequestParam Long postId,
            @RequestParam(defaultValue = "5") int limit) {

        List<RankResponse> ranks = rankService.getConcertTopRanks(postId, limit);

        return ResponseEntity.ok(ApiResponse.success(ranks, "공연 ID: " + postId + " 랭킹 Top " + limit + " 조회 성공"));
    }
}