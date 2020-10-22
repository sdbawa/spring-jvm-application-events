package com.sdbawa.movies.domain.movie.controller.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author simar bawa
 */
@Data
@NoArgsConstructor
public class CreateMovieRequest {
    @Min(2000)
    @Max(2025)
    private Integer year;

    @NotBlank(message = "status is required")
    private String status;

    @NotBlank(message = "title is required")
    private String title;

    @NotNull(message = "budget is required")
    @Digits(integer = 8, fraction = 0)
    private Integer budget;

    @JsonProperty("info")
    @Valid
    @NotNull(message = "action items is required")
    private MovieInfoRequest info;
}
