package com.sdbawa.mmts.domain.actor.controller.exception;

import com.sdbawa.mmts.domain.actor.controller.put.UpdateActorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author simar bawa
 */
@ControllerAdvice
@Slf4j
public class ActorExceptionHandler {

    @ExceptionHandler(ActorException.class)
    public ResponseEntity<UpdateActorResponse> handle(ActorException ex) {
        log.error("ActorExceptionHandler error: {}", ex.getMessage(), ex);
        if (ex.getType() == ActorException.Type.REQUEST_VALIDATION
                        || ex.getType() == ActorException.Type.ACTOR_BUDGET_MOVIE_REQUIRED ) {
            return ResponseEntity.status(400).body(
                            UpdateActorResponse.builder().message(ex.getMessage()).build()
            );
        }

        return ResponseEntity.status(500).body(
                        UpdateActorResponse.builder().message(ex.getMessage()).build()
        );
    }
}
