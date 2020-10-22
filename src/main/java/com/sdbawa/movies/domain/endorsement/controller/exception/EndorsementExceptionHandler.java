package com.sdbawa.movies.domain.endorsement.controller.exception;

import com.sdbawa.movies.domain.endorsement.controller.post.AddEndorsementResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author simar bawa
 */
@ControllerAdvice
@Slf4j
public class EndorsementExceptionHandler {

    @ExceptionHandler(EndorsementException.class)
    public ResponseEntity<AddEndorsementResponse> handle(EndorsementException ex) {
        log.error("EndorsementExceptionHandler error: {}", ex.getMessage(), ex);
        if (ex.getType() == EndorsementException.Type.REQUEST_VALIDATION ) {
            return ResponseEntity.status(400).body(
                            AddEndorsementResponse.builder().message(ex.getMessage()).build()
            );
        }

        return ResponseEntity.status(500).body(
                        AddEndorsementResponse.builder().message(ex.getMessage()).build()
        );
    }
}
