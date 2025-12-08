package com.groove.concert_appetizer.domain.playlist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "playlist_song")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ExpectedPlaylist FK
    @Column(nullable = false)
    private Long playlistId;

    @Column(nullable = false)
    private String songTitle;

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(nullable = false)
    private Boolean isMatch;

    @Builder
    private PlaylistSong(Long playlistId, String songTitle,
                         Integer orderIndex, Boolean isMatch) {
        this.playlistId = playlistId;
        this.songTitle = songTitle;
        this.orderIndex = orderIndex;
        this.isMatch = isMatch;
    }
}
