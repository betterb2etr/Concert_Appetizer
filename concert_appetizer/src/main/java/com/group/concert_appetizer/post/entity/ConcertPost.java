package com.group.concert_appetizer.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "concert_post")
public class ConcertPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String artistName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime concertStart;

    @Column(nullable = false)
    private LocalDateTime concertEnd;

    @Column(nullable = false)
    private LocalDateTime ticketDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private int likeCount;

    @Builder
    public ConcertPost(Long memberId, String artistName, String title,
                       LocalDateTime concertStart, LocalDateTime concertEnd,
                       LocalDateTime ticketDate) {

        this.memberId = memberId;
        this.artistName = artistName;
        this.title = title;
        this.concertStart = concertStart;
        this.concertEnd = concertEnd;
        this.ticketDate = ticketDate;
        this.status = Status.ACTIVE;
        this.likeCount = 0;
    }

    public void update(String title, String artistName,
                       LocalDateTime concertStart, LocalDateTime concertEnd,
                       LocalDateTime ticketDate, Status status) {

        this.title = title;
        this.artistName = artistName;
        this.concertStart = concertStart;
        this.concertEnd = concertEnd;
        this.ticketDate = ticketDate;
        this.status = status;
    }

    public enum Status {
        ACTIVE, BLOCKED
    }
}
