package com.sdbawa.movies.domain.actor.controller.put;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author simar bawa
 */
@Data
@NoArgsConstructor
public class UpdateActorStatusRequest {
    @NotNull(message = "actorId is required")
    private Integer actorId;

    @Min(value = 1, message = "statusId cannot be less than 1")
    @NotNull(message = "statusId is required")
    private Integer statusId;

    private Integer amount;

    private Integer movieId;
}
