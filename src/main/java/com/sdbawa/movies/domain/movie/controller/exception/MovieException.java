package com.sdbawa.movies.domain.movie.controller.exception;

/**
 * @author simar bawa
 */
public class MovieException extends Exception {
    public MovieException(String errorMessage, MovieException.Type type) {
        super(errorMessage);
        this.type = type;
    }

    public enum Type {
        REQUEST_VALIDATION,
        MOVIE_NOT_FOUND_EXCEPTION
    }

    private final MovieException.Type type;

    public MovieException.Type getType() {
        return type;
    }
}
