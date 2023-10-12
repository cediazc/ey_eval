package com.eval.cediaz.evaljava.business;

import com.eval.cediaz.evaljava.domain.PhoneDomain;
import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.entity.Phone;
import com.eval.cediaz.evaljava.entity.User;
import com.eval.cediaz.evaljava.exception.UserException;
import com.eval.cediaz.evaljava.repository.PhoneRepository;
import com.eval.cediaz.evaljava.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Value("${user.active.default}")
    private static Boolean isActive;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public UserDomain registerUser(UserDomain userDomain) {

        // Se busca en la BD si ya está registrado el correo
        User validateUserEntity = userRepository.findByEmail(userDomain.getEmail());

        if(validateUserEntity!= null){
            throw new UserException("El correo ya está registrado anteriormente");
        }

        completeUserInfo(userDomain);
        User userEntity = getUserEntity(userDomain);

        try {
            // Se guarda el User
            userRepository.save(userEntity);

            // Se guarda el listado de Phones
            phoneRepository.saveAll(userEntity.getPhones());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al insertar en la BD");
        }

        return userDomain;
    }

    private void completeUserInfo(UserDomain userDomain) {
        // Se procede a transformar para guardar
        userDomain.setId(UUID.randomUUID().toString());
        userDomain.setToken(getToken(userDomain.getEmail()));
        userDomain.setCreated(new Date());
        userDomain.setLastModified(new Date());
        userDomain.setLastLogin(null);
        userDomain.setActive(isActive);
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

    private String getToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
