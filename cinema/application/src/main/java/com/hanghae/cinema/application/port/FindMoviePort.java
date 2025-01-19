package com.hanghae.cinema.application.port;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.type.MovieGenre;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface FindMoviePort {

    List<MovieResponseDTO> getMovieList(LocalDateTime today, String keyword, MovieGenre genre);
}
