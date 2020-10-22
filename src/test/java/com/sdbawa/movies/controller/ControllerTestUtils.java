package com.sdbawa.movies.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author simar bawa
 */
@Slf4j
public class ControllerTestUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String readJsonStringFromFile(String filePath) {
        try {

            Map<String, Object> request = mapper.readValue(ControllerTestUtils.class.getResourceAsStream(filePath), new TypeReference<Map<String, Object>>(){});
            String apiReq = mapper.writeValueAsString(request);

            return apiReq;
        } catch (Exception e) {
            log.error("Error while converting file to json");
        }
        return null;
    }
}
