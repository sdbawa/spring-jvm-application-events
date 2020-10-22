package com.sdbawa.movies.controller.actor;

import com.sdbawa.movies.domain.actor.controller.ActorController;
import com.sdbawa.movies.controller.ControllerTestUtils;
import com.sdbawa.movies.domain.actor.controller.put.UpdateActorStatusRequest;
import com.sdbawa.movies.domain.statuslookup.exception.StatusLookUpException;
import com.sdbawa.movies.domain.actor.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author simar bawa
 */
@WebMvcTest(controllers = ActorController.class)
@AutoConfigureMockMvc
@Slf4j
public class ActorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ActorService actorService;

    private String testRequestFilePath = "/jsonAPIData/request/";

    @Test
    void updateStatus_whenRequestValid_returnsHTTP200Success() throws Exception {
        Mockito.when(actorService.updateStatus(any(UpdateActorStatusRequest.class))).thenReturn(any(Integer.class));

        String requestFilePathFull = testRequestFilePath + "updateActorStatus_ValidFullRequest.json";
        mvc.perform(
                        put("/actor/status")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(ControllerTestUtils.readJsonStringFromFile(requestFilePathFull))
        ).andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value("success"));
    }

    @Test
    void updateStatus_whenRequestValidAndStatusIdIncorrect_returnsHTTP400() throws Exception {
        var exc = new StatusLookUpException("id not assigned", StatusLookUpException.Type.ID_NOT_ASSIGNED_TO_TYPE);
        Mockito.when(actorService.updateStatus(any(UpdateActorStatusRequest.class))).thenThrow(exc);

        String requestFilePathFull = testRequestFilePath + "updateActorStatus_ValidFullRequest-IncorrectStatus.json";
        mvc.perform(
                        put("/actor/status")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(ControllerTestUtils.readJsonStringFromFile(requestFilePathFull))
        ).andExpect(status().isBadRequest());

    }


}
