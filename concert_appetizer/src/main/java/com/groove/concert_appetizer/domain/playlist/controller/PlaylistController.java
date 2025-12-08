package com.groove.concert_appetizer.domain.playlist.controller;

import com.groove.concert_appetizer.common.api.ApiResponse;
import com.groove.concert_appetizer.domain.playlist.dto.PlaylistCreateRequestDto;
import com.groove.concert_appetizer.domain.playlist.dto.PlaylistCreateResponseDto;
import com.groove.concert_appetizer.domain.playlist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public ApiResponse<PlaylistCreateResponseDto> createPlaylist(
            @RequestHeader("X-MEMBER-ID") Long memberId,  // mock auth
            @RequestBody PlaylistCreateRequestDto request
    ) {
        PlaylistCreateResponseDto response = playlistService.createPlaylist(memberId, request);
        return ApiResponse.success(response);
    }
}
