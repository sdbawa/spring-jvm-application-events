package com.sdbawa.mmts.domain.actor.service;

import com.sdbawa.mmts.domain.actor.repository.ActorRepository;
import com.sdbawa.mmts.domain.statuslookup.StatusLookUpTypeEnum;
import com.sdbawa.mmts.domain.actor.controller.exception.ActorException;
import com.sdbawa.mmts.domain.actor.controller.put.UpdateActorStatusRequest;
import com.sdbawa.mmts.domain.statuslookup.exception.StatusLookUpException;
import com.sdbawa.mmts.domain.events.ActorFeesEvent;
import com.sdbawa.mmts.domain.statuslookup.StatusLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author simar bawa
 */
@Service
@Slf4j
public class ActorService {

    private final StatusLookupService statusLookupService;

    private final ActorRepository actorRepository;

    private final ApplicationEventPublisher eventPublisher;

    public ActorService(StatusLookupService statusLookupService, ActorRepository actorRepository, ApplicationEventPublisher eventPublisher) {
        this.statusLookupService = statusLookupService;
        this.actorRepository = actorRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     *
     * @param userRequest
     * @return
     * @throws StatusLookUpException
     */
    public Integer updateStatus(UpdateActorStatusRequest userRequest) throws StatusLookUpException, ActorException {
        statusLookupService.checkIfStatusIdBelongsToType(userRequest.getStatusId(), StatusLookUpTypeEnum.ACTOR.getValue());

        var existingActorEntity = actorRepository.getOne(userRequest.getActorId());
        if (existingActorEntity == null && existingActorEntity.getId() == null) {
            throw new ActorException("Id do not exist", ActorException.Type.ACTOR_ID_NOT_FOUND);
        }

        if (userRequest.getStatusId().intValue() == 6) {
            if (userRequest.getAmount() == null || userRequest.getAmount() == null) {
                throw new ActorException("Budget and movie both are required for an Actor to sign in contract", ActorException.Type.ACTOR_BUDGET_MOVIE_REQUIRED);
            }

            /*When an actor signs contract for particular movie, their fees is reduced from movie budget*/
            eventPublisher.publishEvent(ActorFeesEvent
                            .builder()
                            .amount(userRequest.getAmount())
                            .movieId(userRequest.getMovieId())
                            .build());

            log.info("event published");
        }


        existingActorEntity.setStatusId(userRequest.getStatusId());
        actorRepository.save(existingActorEntity);
        return existingActorEntity.getId();
    }
}
