package com.sdbawa.mmts.domain.events;

import com.sdbawa.mmts.domain.movie.controller.exception.MovieException;
import com.sdbawa.mmts.domain.events.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author simar bawa
 */
@Component
@Slf4j
public class EventListener {

    private final EventService eventService;

    public EventListener(EventService eventService) {
        this.eventService = eventService;
    }

    @Async
    @org.springframework.context.event.EventListener
    public void handleActorFeesEvent(ActorFeesEvent actorFeesEvent) throws MovieException {
        eventService.applyActorFeesEvent("Actor_Fees_Paid_Event", actorFeesEvent.getAmount(), actorFeesEvent.getMovieId());
        log.info("Success handleActorFeesApplyEventAsync {}", actorFeesEvent);
    }

    @org.springframework.context.event.EventListener
    public void handleEndorsementApplyEvent(EndorsementRevenueEvent endorsementRevenueEvent) throws MovieException {
        eventService.applyEndorsementRevenueEvent("Endorsement_Revenue_Earned_Event", endorsementRevenueEvent.getAmount(), endorsementRevenueEvent.getMovieId());
        log.info("Success handleEndorsementRevenueApplyEventAsync {}", endorsementRevenueEvent);
    }

}
