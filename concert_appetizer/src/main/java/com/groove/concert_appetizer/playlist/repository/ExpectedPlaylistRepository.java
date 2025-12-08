package com.groove.concert_appetizer.playlist.repository;

import com.groove.concert_appetizer.playlist.entity.ExpectedPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpectedPlaylistRepository extends JpaRepository<ExpectedPlaylist, Long> {
}
