package com.sdbawa.mmts.domain.movie.controller.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author simar bawa
 */
@Data
@NoArgsConstructor
public class MovieInfoRequest {
    @NotNull(message = "Minimum one director is required")
    private List<String> directors;

    @NotNull(message = "Minimum one actors is required")
    private List<String> actors;

    private String releaseDate;

    @NotNull(message = "Minimum one genres is required")
    private List<String> genres;

    @Min(1)
    private Integer runningTime;

    private String plot;

    @Min(0)
    private Double rating;
}
