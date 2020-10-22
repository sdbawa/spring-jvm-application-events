package com.sdbawa.mmts.domain.statuslookup.exception;

import com.sdbawa.mmts.domain.statuslookup.StatusLookUpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author simar bawa
 */
@ControllerAdvice
@Slf4j
public class StatusLookUpExceptionHandler {

    @ExceptionHandler(StatusLookUpException.class)
    public ResponseEntity<StatusLookUpResponse> handle(StatusLookUpException ex) {
        log.error("StatusLookUpExceptionHandler error: {}", ex.getMessage(), ex);
        if (ex.getType() == StatusLookUpException.Type.ID_NOT_ASSIGNED_TO_TYPE
                        || ex.getType() == StatusLookUpException.Type.ID_NOT_EXIST_FOR_TYPE_NAME) {
            return ResponseEntity.status(400).body(
                            StatusLookUpResponse.builder().message(ex.getMessage()).build()
            );
        }

        return ResponseEntity.status(500).body(
                        StatusLookUpResponse.builder().message(ex.getMessage()).build()
        );
    }
}
