package com.sdbawa.mmts.domain.events;

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
