package com.sdbawa.mmts.domain.events.service;

import com.sdbawa.mmts.domain.events.repository.MovieBudgetEventsEntity;
import com.sdbawa.mmts.domain.events.repository.MovieBudgetEventsRepository;
import com.sdbawa.mmts.domain.movie.controller.exception.MovieException;
import com.sdbawa.mmts.domain.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author simar bawa
 */
@Service
public class EventService {

    private final MovieRepository movieRepository;
    private final MovieBudgetEventsRepository movieBudgetEventsRepository;

    public EventService(MovieRepository movieRepository, MovieBudgetEventsRepository movieBudgetEventsRepository) {
        this.movieRepository = movieRepository;
        this.movieBudgetEventsRepository = movieBudgetEventsRepository;
    }

    /**
     *
     * @param eventName
     * @param amount
     * @param movieId
     */
    @Transactional
    public void applyActorFeesEvent(String eventName, Integer amount, Integer movieId) throws MovieException {
        var movieEntity = movieRepository.getOne(movieId);

        if (movieEntity == null || movieEntity.getId() == null) {
            throw new MovieException("No movie found for {}" + movieId , MovieException.Type.MOVIE_NOT_FOUND_EXCEPTION);
        }

        movieEntity.setTotalBudget(movieEntity.getTotalBudget() - amount);
        movieRepository.save(movieEntity);

        var movieBudgetEventsEntity = new MovieBudgetEventsEntity();
        movieBudgetEventsEntity.setEvent(eventName);
        movieBudgetEventsEntity.setAmount(amount);
        movieBudgetEventsEntity.setCreateDate(LocalDateTime.now());
        movieBudgetEventsEntity.setMovieId(movieId);

        movieBudgetEventsRepository.save(movieBudgetEventsEntity);
    }

    @Transactional
    public void applyEndorsementRevenueEvent(String eventName, Integer amount, Integer movieId) throws MovieException {
        var movieEntity = movieRepository.getOne(movieId);
        if (movieEntity == null || movieEntity.getId() == null) {
            throw new MovieException("No movie found for {}" + movieId , MovieException.Type.MOVIE_NOT_FOUND_EXCEPTION);
        }

        movieEntity.setTotalBudget(movieEntity.getTotalBudget() + amount);
        movieRepository.save(movieEntity);

        var movieBudgetEventsEntity = new MovieBudgetEventsEntity();
        movieBudgetEventsEntity.setEvent(eventName);
        movieBudgetEventsEntity.setAmount(amount);
        movieBudgetEventsEntity.setCreateDate(LocalDateTime.now());
        movieBudgetEventsEntity.setMovieId(movieId);

        movieBudgetEventsRepository.save(movieBudgetEventsEntity);
    }

}
