package com.group.concert_appetizer.post.service;

import com.group.concert_appetizer.post.dto.CreatePostRequest;
import com.group.concert_appetizer.post.dto.PostResponse;
import com.group.concert_appetizer.post.entity.ConcertPost;
import com.group.concert_appetizer.post.repository.ConcertPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConcertPostService {

    private final ConcertPostRepository postRepository;

    public PostResponse create(CreatePostRequest request) {

        ConcertPost post = ConcertPost.builder()
                .memberId(request.getMemberId())
                .artistName(request.getArtistName())
                .title(request.getTitle())
                .concertStart(request.getConcertStart())
                .concertEnd(request.getConcertEnd())
                .ticketDate(request.getTicketDate())
                .build();

        return PostResponse.fromEntity(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAll() {
        return postRepository.findByStatus(ConcertPost.Status.ACTIVE)
                .stream()
                .map(PostResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse get(Long postId) {
        ConcertPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with id " + postId + " not found"));

        return PostResponse.fromEntity(post);
    }

    public PostResponse update(Long postId, CreatePostRequest request) {
        ConcertPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with id " + postId + " not found"));

        post.update(
                request.getTitle(),
                request.getArtistName(),
                request.getConcertStart(),
                request.getConcertEnd(),
                request.getTicketDate(),
                ConcertPost.Status.ACTIVE
        );

        return PostResponse.fromEntity(post);
    }

    public void delete(Long postId) {
        ConcertPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with id " + postId + " not found"));

        post.update(
                post.getTitle(),
                post.getArtistName(),
                post.getConcertStart(),
                post.getConcertEnd(),
                post.getTicketDate(),
                ConcertPost.Status.BLOCKED
        );
    }
}

