package com.hanghae.cinema.adapter.persistence.repository;

import com.hanghae.cinema.adapter.persistence.entity.MovieEntity;
import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.type.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, MovieRepositoryCustom {
    @Query("""
                    select m.id, m.title, m.thumbNail, m.rating, m.releaseAt, m.runningTime, m.genre,
                    s.id, s.startedAt, s.endedAt, t.id, t.name
                    from MovieEntity m
                    join ShowTimeEntity s on m.id = s.movie.id
                            join TheaterEntity t on t.id = s.theater.id
                            where m.releaseAt <= :date and s.startedAt > :dateTime
                            order by m.releaseAt DESC, s.startedAt ASC
            """)
    List<Object[]> findMoviesWithShowTimesAndTheaters(LocalDate date, LocalDateTime dateTime);

    List<MovieResponseDTO> findMovie(LocalDate date, LocalDateTime dateTime, String keyword, MovieGenre genre);
}