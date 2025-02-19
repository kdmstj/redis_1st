package com.hanghae.cinema.application.dto;

import com.hanghae.cinema.domain.type.MovieGenre;
import com.hanghae.cinema.domain.type.MovieRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String thumbNail;
    private MovieRating rating;
    private LocalDate releaseAt;
    private Integer runningTime;
    private MovieGenre genre;
    private Set<ShowTimeResponseDTO> showTime;
}
