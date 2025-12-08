package com.groove.concert_appetizer.playlist.repository;

import com.groove.concert_appetizer.playlist.entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    List<PlaylistSong> findByPlaylistIdOrderByOrderIndex(Long playlistId);
}
