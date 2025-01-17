package com.hanghae.cinema.adapter.persistence;

import com.hanghae.cinema.adapter.persistence.entity.MovieEntity;
import com.hanghae.cinema.adapter.persistence.mapper.MovieEntityMapper;
import com.hanghae.cinema.adapter.persistence.repository.MovieRepository;
import com.hanghae.cinema.application.port.FindMoviePort;
import com.hanghae.cinema.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MoviePersistenceAdapter implements FindMoviePort {

    private final MovieRepository movieRepository;
    private final MovieEntityMapper movieEntityMapper;

    @Override
    public List<Movie> getMovieList(LocalDate date) {
        List<MovieEntity> movieEntityList = movieRepository.findAllByReleaseAtLessThanEqual(date, Sort.by(Sort.Direction.DESC, "releaseDate"));

        return movieEntityList.stream()
                .map(movieEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
