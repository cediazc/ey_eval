package com.eval.cediaz.evaljava.business;

import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.repository.PhoneRepository;
import com.eval.cediaz.evaljava.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @Mock
    PhoneRepository phoneRepository;

    @BeforeEach
    void init(){
        userService = new UserServiceImpl();
    }


    private static String MOCK_PATH = "src/test/resources/mock/";

    @Test
    void whenRegisterUser_thenRetursUserDomain() throws IOException {
        UserDomain userDomain = buildUserDomain();

        when(userRepository.findByEmail(any())).thenReturn(null);
        doNothing().when(userRepository.save(any()));
        doNothing().when(phoneRepository.saveAll(any()));

        userService.registerUser(userDomain);

        assertNotNull(userDomain.getEmail());

    }

    private UserDomain buildUserDomain() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenPayloadIsValid.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        // Convierte el JSON en un objeto Java utilizando Jackson
        return objectMapper.readValue(jsonContent, UserDomain.class);
    }

}
