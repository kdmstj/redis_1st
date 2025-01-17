package com.hanghae.cinema.application.mapper;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponseDTO toResponseDTO(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .thumbNail(movie.getThumbNail())
                .rating(movie.getRating())
                .releaseAt(movie.getReleaseAt())
                .runningTime(movie.getRunningTime())
                .genre(movie.getGenre())
                .build();
    }
}
