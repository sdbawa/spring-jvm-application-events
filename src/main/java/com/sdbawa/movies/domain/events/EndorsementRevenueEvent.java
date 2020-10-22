package com.sdbawa.movies.domain.events;

import lombok.Builder;
import lombok.Getter;

/**
 * @author simar bawa
 */
@Getter
@Builder
public class EndorsementRevenueEvent {
    Integer amount;
    Integer movieId;
}
