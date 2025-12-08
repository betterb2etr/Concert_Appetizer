package com.groove.concert_appetizer.domain.playlist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "expected_playlist")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ExpectedPlaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ConcertPost FK
    @Column(nullable = false)
    private Long postId;

    // Member FK (mock auth: header 의 memberId 사용)
    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Integer hitRate;   // 0 ~ 100

    @Column(nullable = false)
    private Integer likeCount;

    @Builder
    private ExpectedPlaylist(Long postId, Long memberId, String title,
                             Integer hitRate, Integer likeCount) {
        this.postId = postId;
        this.memberId = memberId;
        this.title = title;
        this.hitRate = hitRate;
        this.likeCount = likeCount;
    }

    public void updateHitRate(int hitRate) {
        this.hitRate = hitRate;
    }
}
