package com.sdbawa.movies.domain.endorsement.controller;

import com.sdbawa.movies.domain.endorsement.controller.exception.EndorsementException;
import com.sdbawa.movies.domain.endorsement.controller.post.AddEndorsementRequest;
import com.sdbawa.movies.domain.endorsement.controller.post.AddEndorsementResponse;
import com.sdbawa.movies.common.ControllerUtils;
import com.sdbawa.movies.domain.events.EndorsementRevenueEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author simar bawa
 */
@RestController
@Slf4j
public class EndorsementController {

    private final ApplicationEventPublisher eventPublisher;

    public EndorsementController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * When an endorsement is signed the revenue gets added to budget
     */
    @PostMapping("/endorsement/add")
    public ResponseEntity<AddEndorsementResponse> addEndorsement(@Valid @RequestBody AddEndorsementRequest request,
                    BindingResult bindingResult)
                    throws EndorsementException {

        if (bindingResult.hasErrors()) {
            throw new EndorsementException(ControllerUtils.getValidationErrors(bindingResult),
                            EndorsementException.Type.REQUEST_VALIDATION);
        }

        try {
            eventPublisher.publishEvent(EndorsementRevenueEvent
                            .builder()
                            .amount(request.getAmount())
                            .movieId(request.getMovieId())
                            .build());
        } catch (Exception e) {
            throw new EndorsementException("Error while adding endorsement. Please check input again",
                            EndorsementException.Type.INTERNAL_ERROR);
        }


        return ResponseEntity
                        .ok(AddEndorsementResponse
                                        .builder()
                                        .message("success")
                                        .build());
    }
}
