package com.groove.concert_appetizer.domain.playlist.repository;

import com.groove.concert_appetizer.domain.playlist.entity.ExpectedPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpectedPlaylistRepository extends JpaRepository<ExpectedPlaylist, Long> {

    List<ExpectedPlaylist> findByPostId(Long postId);

    List<ExpectedPlaylist> findByMemberId(Long memberId);

    List<ExpectedPlaylist> findByPostIdAndMemberId(Long postId, Long memberId);
}
