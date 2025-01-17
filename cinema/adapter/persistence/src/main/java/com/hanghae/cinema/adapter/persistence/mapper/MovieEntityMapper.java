package com.hanghae.cinema.adapter.persistence.mapper;

import com.hanghae.cinema.adapter.persistence.entity.MovieEntity;
import com.hanghae.cinema.domain.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieEntityMapper {
    public Movie toDomain(MovieEntity entity) {
        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .thumbNail(entity.getThumbNail())
                .rating(entity.getRating())
                .runningTime(entity.getRunningTime())
                .genre(entity.getGenre())
                .build();
    }
}
