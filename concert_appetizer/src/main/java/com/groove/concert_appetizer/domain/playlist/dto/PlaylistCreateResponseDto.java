package com.groove.concert_appetizer.domain.playlist.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaylistCreateResponseDto {

    private final Long playlistId;
    private final int hitRate;              // 0~100
    private final int matchedSongCount;
    private final int totalSongs;
    private final List<Integer> matchedPositions;

    @Builder
    private PlaylistCreateResponseDto(Long playlistId, int hitRate,
                                      int matchedSongCount, int totalSongs,
                                      List<Integer> matchedPositions) {
        this.playlistId = playlistId;
        this.hitRate = hitRate;
        this.matchedSongCount = matchedSongCount;
        this.totalSongs = totalSongs;
        this.matchedPositions = matchedPositions;
    }
}
