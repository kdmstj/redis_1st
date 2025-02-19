package com.hanghae.cinema.adapter.persistence;

import com.hanghae.cinema.adapter.persistence.repository.MovieRepository;
import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.dto.ShowTimeResponseDTO;
import com.hanghae.cinema.application.dto.TheaterResponseDTO;
import com.hanghae.cinema.application.port.FindMoviePort;
import com.hanghae.cinema.domain.type.MovieGenre;
import com.hanghae.cinema.domain.type.MovieRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Component
public class MoviePersistenceAdapter implements FindMoviePort {

    private final MovieRepository movieRepository;

    public List<MovieResponseDTO> getMovieList(LocalDateTime dateTime) {
        List<Object[]> results = movieRepository.findMoviesWithShowTimesAndTheaters(dateTime.toLocalDate(), dateTime);

        Map<Long, MovieResponseDTO> movieMap = new HashMap<>();

        for (Object[] row : results) {
            Long movieId = (Long) row[0];
            String title = (String) row[1];
            String thumbNail = (String) row[2];
            MovieRating rating = (MovieRating) row[3];
            LocalDate releaseAt = (LocalDate) row[4];
            Integer runningTime = (Integer) row[5];
            MovieGenre genre = (MovieGenre) row[6];

            Long showTimeId = (Long) row[7];
            LocalDateTime startedAt = (LocalDateTime) row[8];
            LocalDateTime endedAt = (LocalDateTime) row[9];

            Long theaterId = (Long) row[10];
            String theaterName = (String) row[11];

            MovieResponseDTO movieDTO = movieMap.computeIfAbsent(movieId, id -> MovieResponseDTO.builder()
                    .id(movieId)
                    .title(title)
                    .thumbNail(thumbNail)
                    .rating(rating)
                    .releaseAt(releaseAt)
                    .runningTime(runningTime)
                    .genre(genre)
                    .showTime(new HashSet<>())
                    .build()
            );

            ShowTimeResponseDTO showTimeDTO = ShowTimeResponseDTO.builder()
                    .id(showTimeId)
                    .startedAt(startedAt)
                    .endedAt(endedAt)
                    .theater(TheaterResponseDTO.builder()
                            .id(theaterId)
                            .name(theaterName)
                            .build())
                    .build();

            movieDTO.getShowTime().add(showTimeDTO);
        }

        return new ArrayList<>(movieMap.values());
    }
}
