package com.eval.cediaz.evaljava.controller;

import com.eval.cediaz.evaljava.business.UserService;
import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.exception.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserDomainControllerTest {

    @Autowired
    private MockMvc controller;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private static String MOCK_PATH = "src/test/resources/mock/";

    @Test
    void whenPayloadIsValid_thenRetursStatus200() throws Exception {

        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenPayloadIsValid.json")));

        // Convierte el JSON en un objeto Java utilizando Jackson
        UserDomain userDomain = objectMapper.readValue(jsonContent, UserDomain.class);

        String body = objectMapper.writeValueAsString(userDomain);

        controller.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void whenEmailIsInvalid_thenRetursStatus400() throws Exception {

        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenEmailIsInvalid.json")));

        // Convierte el JSON en un objeto Java utilizando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        UserDomain userDomain = objectMapper.readValue(jsonContent, UserDomain.class);

        String body = objectMapper.writeValueAsString(userDomain);

        controller.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPasswordIsInvalid_thenRetursStatus400() throws Exception {

        //final String msjPasswordIsInvalid = "El email debe tener el formato xxxxx@xxx.xx";

        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenPasswordIsInvalid.json")));

        // Convierte el JSON en un objeto Java utilizando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        UserDomain userDomain = objectMapper.readValue(jsonContent, UserDomain.class);

        String body = objectMapper.writeValueAsString(userDomain);

        controller.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenEmailIsDuplicated_thenRetursStatus409() throws Exception {

        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenEmailIsDuplicated.json")));

        // Convierte el JSON en un objeto Java utilizando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        UserDomain userDomain = objectMapper.readValue(jsonContent, UserDomain.class);

        String body = objectMapper.writeValueAsString(userDomain);

        when(userService.registerUser(any())).thenThrow(new UserException(""));

        controller.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isConflict());
    }

    @Test
    void whenPayloadIsValid_thenRetursStatus500() throws Exception {

        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenPayloadIsValid.json")));

        when(userService.registerUser(any())).thenThrow(new IllegalArgumentException());
        // Convierte el JSON en un objeto Java utilizando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        UserDomain userDomain = objectMapper.readValue(jsonContent, UserDomain.class);

        String body = objectMapper.writeValueAsString(userDomain);

        controller.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isInternalServerError());
    }

}