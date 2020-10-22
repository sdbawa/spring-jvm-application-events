package com.sdbawa.movies.domain.endorsement.controller.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author simar bawa
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEndorsementResponse {
    private String message;
}
