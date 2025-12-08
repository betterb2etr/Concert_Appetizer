package com.groove.concert_appetizer.domain.playlist.repository;

import com.groove.concert_appetizer.domain.playlist.entity.ActualSetlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActualSetlistRepository extends JpaRepository<ActualSetlist, Long> {

    List<ActualSetlist> findByPostId(Long postId);
}
