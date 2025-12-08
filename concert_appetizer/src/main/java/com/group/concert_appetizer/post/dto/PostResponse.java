package com.group.concert_appetizer.post.dto;

import com.group.concert_appetizer.post.entity.ConcertPost;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long postId;
    private String artistName;
    private String title;
    private LocalDateTime concertStart;
    private LocalDateTime concertEnd;
    private LocalDateTime ticketDate;
    private String status;
    private int likeCount;

    @Builder
    public PostResponse(Long postId, String artistName, String title,
                        LocalDateTime concertStart, LocalDateTime concertEnd,
                        LocalDateTime ticketDate, String status, int likeCount) {

        this.postId = postId;
        this.artistName = artistName;
        this.title = title;
        this.concertStart = concertStart;
        this.concertEnd = concertEnd;
        this.ticketDate = ticketDate;
        this.status = status;
        this.likeCount = likeCount;
    }

    public static PostResponse fromEntity(ConcertPost post) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .artistName(post.getArtistName())
                .title(post.getTitle())
                .concertStart(post.getConcertStart())
                .concertEnd(post.getConcertEnd())
                .ticketDate(post.getTicketDate())
                .status(post.getStatus().name())
                .likeCount(post.getLikeCount())
                .build();
    }
}

