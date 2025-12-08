package com.groove.concert_appetizer.domain.playlist.service;

import com.groove.concert_appetizer.domain.playlist.dto.PlaylistCreateRequestDto;
import com.groove.concert_appetizer.domain.playlist.dto.PlaylistCreateResponseDto;
import com.groove.concert_appetizer.domain.playlist.entity.ActualSetlist;
import com.groove.concert_appetizer.domain.playlist.entity.ExpectedPlaylist;
import com.groove.concert_appetizer.domain.playlist.entity.PlaylistSong;
import com.groove.concert_appetizer.domain.playlist.repository.ActualSetlistRepository;
import com.groove.concert_appetizer.domain.playlist.repository.ExpectedPlaylistRepository;
import com.groove.concert_appetizer.domain.playlist.repository.PlaylistSongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final ActualSetlistRepository actualSetlistRepository;
    private final ExpectedPlaylistRepository expectedPlaylistRepository;
    private final PlaylistSongRepository playlistSongRepository;
    private final HitRateCalculator hitRateCalculator = new HitRateCalculator();

    @Transactional
    public PlaylistCreateResponseDto createPlaylist(Long memberId, PlaylistCreateRequestDto request) {

        // 1. 정답 셋리스트 조회
        List<ActualSetlist> actualList = actualSetlistRepository
                .findByPostId(request.getPostId())
                .stream()
                .sorted(Comparator.comparing(ActualSetlist::getOrderIndex))
                .toList();

        List<String> actualTitles = actualList.stream()
                .map(ActualSetlist::getSongTitle)
                .toList();

        // 2. 적중률 계산
        HitRateCalculator.Result result = hitRateCalculator.calculate(actualTitles, request.getSongs());

        // 3. ExpectedPlaylist 저장
        ExpectedPlaylist playlist = ExpectedPlaylist.builder()
                .postId(request.getPostId())
                .memberId(memberId)
                .title(request.getTitle())
                .hitRate(result.getHitRate())
                .likeCount(0)
                .build();

        ExpectedPlaylist saved = expectedPlaylistRepository.save(playlist);

        // 4. PlaylistSongs 저장 (각 곡별 isMatch 표시)
        for (int i = 0; i < request.getSongs().size(); i++) {
            int orderIndex = i + 1;
            boolean isMatch = result.getMatchedPositions().contains(orderIndex);

            PlaylistSong song = PlaylistSong.builder()
                    .playlistId(saved.getId())
                    .songTitle(request.getSongs().get(i))
                    .orderIndex(orderIndex)
                    .isMatch(isMatch)
                    .build();

            playlistSongRepository.save(song);
        }

        // 5. 응답 DTO 반환
        return PlaylistCreateResponseDto.builder()
                .playlistId(saved.getId())
                .hitRate(result.getHitRate())
                .matchedSongCount(result.getMatchedCount())
                .totalSongs(result.getTotalSongs())
                .matchedPositions(result.getMatchedPositions())
                .build();
    }
}
