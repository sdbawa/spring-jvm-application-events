package com.sdbawa.mmts.domain.movie.controller.exception;

import com.sdbawa.mmts.domain.movie.controller.post.CreateMovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author simar bawa
 */
@ControllerAdvice
@Slf4j
public class MovieExceptionHandler {

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<CreateMovieResponse> handle(MovieException ex) {
        log.error("ActorExceptionHandler error: {}", ex.getMessage(), ex);
        if (ex.getType() == MovieException.Type.REQUEST_VALIDATION) {
            return ResponseEntity.status(400).body(
                            CreateMovieResponse.builder().message(ex.getMessage()).build()
            );
        }

        return ResponseEntity.status(500).body(
                        CreateMovieResponse.builder().message(ex.getMessage()).build()
        );
    }

}
