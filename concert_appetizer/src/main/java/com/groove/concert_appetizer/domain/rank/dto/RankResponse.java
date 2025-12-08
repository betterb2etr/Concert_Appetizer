package com.groove.concert_appetizer.domain.rank.dto;

import com.groove.concert_appetizer.domain.playlist.entity.ExpectedPlaylist;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 랭킹 목록의 각 항목을 나타내는 DTO입니다.
// 사용자에게 명예의 전당 정보를 깔끔하게 보여주기 위해 사용됩니다.
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class RankResponse {
    private Long playlistId;
    private String playlistTitle;
    private String memberNickname; // 예상 플리를 만든 사용자 닉네임
    private String concertPostTitle; // 해당 공연 게시글 제목
    private int hitRate; // 적중률 (핵심 랭킹 기준)
    private int likeCount; // 추천 수 (보조 랭킹 기준)
    private LocalDateTime createdAt;

    @Builder
    public RankResponse(Long playlistId, String playlistTitle, String memberNickname, String concertPostTitle, int hitRate, int likeCount, LocalDateTime createdAt) {
        this.playlistId = playlistId;
        this.playlistTitle = playlistTitle;
        this.memberNickname = memberNickname;
        this.concertPostTitle = concertPostTitle;
        this.hitRate = hitRate;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    // Entity에서 DTO로 변환하는 정적 팩토리 메서드입니다.
    // '조인' 관계를 통해 Member와 ConcertPost의 정보를 가져와야 하지만,
    // 해커톤의 간소화된 DB 스키마와 요구사항을 고려하여, 실제로는
    // Repository에서 DTO 형태로 직접 Projection 하거나(권장), Service에서 조회 후 변환해야 합니다.
    // 현재는 코드를 최소화하기 위해 Service에서 변환한다고 가정하고, 필수적인 정보만 담습니다.
    public static RankResponse of(ExpectedPlaylist playlist) {
        // 실제 운영 환경에서는 N+1 문제를 방지하기 위해 Fetch Join 또는 DTO Projection이 필수입니다.
        return RankResponse.builder()
                .playlistId(playlist.getId())
                .playlistTitle(playlist.getTitle())
                // Member와 ConcertPost는 ManyToOne 관계로 가정합니다.
                // 해커톤 요구사항에서 "연관관계를 최소화한다"고 했으므로, 지금은 Mock 데이터를 사용하거나
                // Post, Member 정보를 별도로 조회한다고 가정하겠습니다.
                // 하지만 DB 스키마에 FK가 명시되어 있으므로, 연관관계는 있다고 가정하고 코드를 작성합니다.
                .memberNickname(playlist.getMember().getNickname())
                .concertPostTitle(playlist.getConcertPost().getTitle())
                .hitRate(playlist.getHitRate())
                .likeCount(playlist.getLikeCount())
                .createdAt(playlist.getCreatedAt())
                .build();
    }
}