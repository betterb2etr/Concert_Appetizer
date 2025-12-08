package com.groove.concert_appetizer.domain.playlist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlaylistCreateRequestDto {

    private Long postId;           // 어떤 공연에 대한 플리인지
    private String title;          // 유저가 붙인 플리 제목
    private List<String> songs;    // romaji 기준 곡명 리스트 (순서 포함)
}
