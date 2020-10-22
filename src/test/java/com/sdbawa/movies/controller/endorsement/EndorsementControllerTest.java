package com.sdbawa.movies.controller.endorsement;

import com.sdbawa.movies.domain.endorsement.controller.EndorsementController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author simar bawa
 */
@WebMvcTest(controllers = EndorsementController.class)
@AutoConfigureMockMvc
@Slf4j
public class EndorsementControllerTest {

    @Autowired
    private MockMvc mvc;

    private String testRequestFilePath = "/jsonAPIData/request/";
}
