package com.sdbawa.movies.domain.actor.controller.exception;


/**
 * @author simar bawa
 */
public class ActorException extends Exception {
    public ActorException(String errorMessage, ActorException.Type type) {
        super(errorMessage);
        this.type = type;
    }

    public enum Type {
        REQUEST_VALIDATION,
        ACTOR_ID_NOT_FOUND,
        ACTOR_BUDGET_MOVIE_REQUIRED
    }

    private final ActorException.Type type;

    public ActorException.Type getType() {
        return type;
    }
}
