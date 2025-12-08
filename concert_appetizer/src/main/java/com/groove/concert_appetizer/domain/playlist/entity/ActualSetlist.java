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
@Table(name = "actual_setlist")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ActualSetlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ConcertPost FK - 일단 Long 값만 보관 (다른 BC와 연관관계 최소화)
    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String songTitle;   // romaji 기준으로 저장

    @Column(nullable = false)
    private Integer orderIndex;

    @Builder
    private ActualSetlist(Long postId, String songTitle, Integer orderIndex) {
        this.postId = postId;
        this.songTitle = songTitle;
        this.orderIndex = orderIndex;
    }
}
