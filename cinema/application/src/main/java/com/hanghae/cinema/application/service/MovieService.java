package com.hanghae.cinema.application.service;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.port.FindMoviePort;
import com.hanghae.cinema.application.usecase.FindMovieUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService implements FindMovieUseCase {

    private final FindMoviePort findMoviePort;

    @Override
    public List<MovieResponseDTO> getMovies() {

        LocalDateTime today = LocalDateTime.now();

        return findMoviePort.getMovieList(today);
    }
}