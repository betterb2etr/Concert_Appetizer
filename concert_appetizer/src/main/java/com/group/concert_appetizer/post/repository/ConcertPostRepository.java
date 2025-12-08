package com.group.concert_appetizer.post.repository;

import com.group.concert_appetizer.post.entity.ConcertPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertPostRepository extends JpaRepository<ConcertPost, Long> {
    List<ConcertPost> findByStatus(ConcertPost.Status status);
}

