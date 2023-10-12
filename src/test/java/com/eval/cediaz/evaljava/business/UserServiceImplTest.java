package com.eval.cediaz.evaljava.business;

import com.eval.cediaz.evaljava.domain.PhoneDomain;
import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.entity.Phone;
import com.eval.cediaz.evaljava.entity.User;
import com.eval.cediaz.evaljava.repository.PhoneRepository;
import com.eval.cediaz.evaljava.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @Mock
    PhoneRepository phoneRepository;

    @Test
    void whenRegisterUser_thenRetursUserDomain() throws IOException {
        UserDomain userDomain = buildUserDomain();
        ReflectionTestUtils.setField(userService, "secretKey" , "5d8cf81e187b792d80f08b4dc5a4f5157f889170a8eed2f1b5b8c64ca58e89dc");

        when(userRepository.findByEmail(any())).thenReturn(null);

        completeUserInfo(userDomain);
        User userEntity = getUserEntity(userDomain);

        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(phoneRepository.saveAll(anyList())).thenReturn(userEntity.getPhones());

        userService.registerUser(userDomain);

        assertNotNull(userDomain.getEmail());

    }

    private UserDomain buildUserDomain() throws IOException {
        String MOCK_PATH = "src/test/resources/mock/";
        String jsonContent = new String(Files.readAllBytes(Paths.get(MOCK_PATH + "whenPayloadIsValid.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        // Convierte el JSON en un objeto Java utilizando Jackson
        return objectMapper.readValue(jsonContent, UserDomain.class);
    }

    private User getUserEntity(UserDomain userDomain) {
        User userEntity = new User();

        userEntity.setId(userDomain.getId());
        userEntity.setName(userDomain.getName());
        userEntity.setEmail(userDomain.getEmail());
        userEntity.setPassword(userDomain.getPassword());
        userEntity.setCreated(userDomain.getCreated());
        userEntity.setLastModified(userDomain.getLastModified());
        userEntity.setLastLogin(userDomain.getLastLogin());
        userEntity.setActive(userDomain.getActive());
        userEntity.setToken(userDomain.getToken());

        List<Phone> phones = userDomain.getPhones().stream()
                .map((PhoneDomain phoneDomain) -> getPhoneEntity(phoneDomain, userEntity))
                .toList();

        userEntity.setPhones(phones);

        return userEntity;
    }

    private Phone getPhoneEntity(PhoneDomain phoneDomain, User userEntity){
        Phone phoneEntity = new Phone();

        phoneEntity.setUser(userEntity);
        phoneEntity.setCityCode(phoneDomain.getCityCode());
        phoneEntity.setCountryCode(phoneDomain.getCountryCode());
        phoneEntity.setNumber(phoneDomain.getNumber());

        return phoneEntity;
    }

    private void completeUserInfo(UserDomain userDomain) {
        userDomain.setId("UUID");
        userDomain.setToken("token");
        userDomain.setCreated(new Date());
        userDomain.setLastModified(new Date());
        userDomain.setLastLogin(null);
        userDomain.setActive(true);
    }

}
