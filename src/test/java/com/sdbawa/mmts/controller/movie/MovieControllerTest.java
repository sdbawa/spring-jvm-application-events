package com.sdbawa.mmts.controller.movie;

import com.sdbawa.mmts.controller.ControllerTestUtils;
import com.sdbawa.mmts.domain.movie.controller.MovieController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsStringIgnoringCase;

/**
 * @author simar bawa
 */
@WebMvcTest(controllers = MovieController.class)
@AutoConfigureMockMvc
@Slf4j
public class MovieControllerTest {
    @Autowired
    private MockMvc mvc;

    private String testRequestFilePath = "/jsonAPIData/request/";

    @Test
    void createMovie_whenRequestValid_returnsHTTP200Success() throws Exception {
        String requestFilePathFull = testRequestFilePath + "createMovieRequest_ValidFullRequest.json";
        mvc.perform(
                        post("/movie")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(ControllerTestUtils.readJsonStringFromFile(requestFilePathFull))
        ).andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("success"));
    }


    @Test
    void createMovie_whenRequestIsInValid_returnsHTTP400WithValidationFailureResults() throws Exception {
        String requestFilePathFull = testRequestFilePath + "createMovieRequest_InValidRequest-MissingDirectors-Genre-Status.json";
        mvc.perform(
                        post("/movie")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(ControllerTestUtils.readJsonStringFromFile(requestFilePathFull))
        ).andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").isNotEmpty())
                        .andExpect(jsonPath("$.message", containsStringIgnoringCase("status is required")))
                        .andExpect(jsonPath("$.message", containsStringIgnoringCase("Minimum one genres is required")))
                        .andExpect(jsonPath("$.message", containsStringIgnoringCase("Minimum one director is required")))
                        .andExpect(jsonPath("$.message", containsStringIgnoringCase("budget is required")));

    }
}
