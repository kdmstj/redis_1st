package com.hanghae.cinema.domain.model;

import com.hanghae.cinema.domain.type.MovieGenre;
import com.hanghae.cinema.domain.type.MovieRating;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Movie {

    private Long id;
    private String title;
    private String thumbNail;
    private MovieRating rating;
    private LocalDate releaseAt;
    private Integer runningTime; //시간 단위 (분)
    private MovieGenre genre;
}
