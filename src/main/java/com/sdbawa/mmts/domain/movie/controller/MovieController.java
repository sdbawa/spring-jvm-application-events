package com.sdbawa.mmts.domain.movie.controller;

import com.sdbawa.mmts.domain.movie.controller.post.CreateMovieResponse;
import com.sdbawa.mmts.domain.movie.controller.exception.MovieException;
import com.sdbawa.mmts.domain.movie.controller.post.CreateMovieRequest;
import com.sdbawa.mmts.common.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author simar bawa
 */
@RestController
@Slf4j
public class MovieController {

    @PostMapping("/movie")
    public ResponseEntity<CreateMovieResponse> createMovie(@Valid @RequestBody CreateMovieRequest request,
                    BindingResult bindingResult)
                    throws MovieException {

        if (bindingResult.hasErrors()) {
            throw new MovieException(ControllerUtils.getValidationErrors(bindingResult), MovieException.Type.REQUEST_VALIDATION);
        }

        return ResponseEntity.ok(CreateMovieResponse.builder().message("success").build());
    }
}
