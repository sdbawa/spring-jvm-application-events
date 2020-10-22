package com.sdbawa.movies.domain.endorsement.controller.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author simar bawa
 */
@Data
@NoArgsConstructor
public class AddEndorsementRequest {

    @NotNull(message = "movieId is required")
    private Integer movieId;

    @NotNull(message = "amount is required")
    private Integer amount;

    @NotNull(message = "endorsementId is required")
    private Integer endorsementId;


}
