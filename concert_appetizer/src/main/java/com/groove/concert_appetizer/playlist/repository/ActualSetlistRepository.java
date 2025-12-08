package com.groove.concert_appetizer.playlist.repository;

import com.groove.concert_appetizer.playlist.entity.ActualSetlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActualSetlistRepository extends JpaRepository<ActualSetlist, Long> {
    List<ActualSetlist> findByPostIdOrderByOrderIndex(Long postId);

}
