package com.groove.concert_appetizer.domain.rank.service;

import com.groove.concert_appetizer.domain.playlist.ExpectedPlaylistRepository;
import com.groove.concert_appetizer.domain.playlist.entity.ExpectedPlaylist;
import com.groove.concert_appetizer.domain.rank.dto.RankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {

    private final ExpectedPlaylistRepository expectedPlaylistRepository;

    /**
     * 전체 서비스의 명예의 전당 상위 랭킹 리스트를 조회합니다.
     * @param limit 조회할 랭킹의 개수 (예: Top 10)
     * @return 랭킹 DTO 리스트
     */
    public List<RankResponse> getOverallTopRanks(int limit) {
        // Pageable 객체를 사용하여 상위 'limit'개만 조회하도록 설정합니다.
        Pageable pageable = PageRequest.of(0, limit);

        List<ExpectedPlaylist> topPlaylists = expectedPlaylistRepository.findTopRankedPlaylists(pageable);

        // 조회된 Entity 리스트를 RankResponse DTO 리스트로 변환합니다.
        return topPlaylists.stream()
                .map(RankResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 특정 공연에 대한 예상 셋리스트 랭킹을 조회합니다.
     * @param postId 대상 공연 게시글 ID
     * @param limit 조회할 랭킹의 개수
     * @return 랭킹 DTO 리스트
     */
    public List<RankResponse> getConcertTopRanks(Long postId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);

        // Repository에서 postId로 필터링된 랭킹 조회 메서드를 사용합니다.
        // 이 메서드는 ExpectedPlaylistRepository에 추가되어야 합니다.
        List<ExpectedPlaylist> topPlaylists = expectedPlaylistRepository.findTopRankedPlaylistsByConcertPostId(postId, pageable);

        return topPlaylists.stream()
                .map(RankResponse::of)
                .collect(Collectors.toList());
    }
}