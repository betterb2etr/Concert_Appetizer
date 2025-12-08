package com.group.concert_appetizer.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostRequest {

    @NotNull
    private Long memberId;

    @NotBlank
    private String artistName;

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime concertStart;

    @NotNull
    private LocalDateTime concertEnd;

    @NotNull
    private LocalDateTime ticketDate;
}

