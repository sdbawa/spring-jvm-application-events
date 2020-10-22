package com.sdbawa.mmts.domain.actor.controller;

import com.sdbawa.mmts.domain.actor.controller.exception.ActorException;
import com.sdbawa.mmts.domain.actor.controller.put.UpdateActorResponse;
import com.sdbawa.mmts.domain.actor.controller.put.UpdateActorStatusRequest;
import com.sdbawa.mmts.common.ControllerUtils;
import com.sdbawa.mmts.domain.statuslookup.exception.StatusLookUpException;
import com.sdbawa.mmts.domain.actor.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author simar bawa
 */
@RestController
@Slf4j
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PutMapping("/actor/status")
    public ResponseEntity<UpdateActorResponse> updateStatus(@Valid @RequestBody UpdateActorStatusRequest request,
                    BindingResult bindingResult)
                    throws ActorException, StatusLookUpException {

        if (bindingResult.hasErrors()) {
            throw new ActorException(ControllerUtils.getValidationErrors(bindingResult),
                            ActorException.Type.REQUEST_VALIDATION);
        }

        actorService.updateStatus(request);

        return ResponseEntity
                        .ok(UpdateActorResponse
                                        .builder()
                                        .message("success")
                                        .build());
    }
}
