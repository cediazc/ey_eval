package com.eval.cediaz.evaljava.mapper;

import com.eval.cediaz.evaljava.domain.PhoneDomain;
import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.entity.Phone;
import com.eval.cediaz.evaljava.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserMapperServiceImpl implements UserMapperService {

    @Value("${user.active.default}")
    private static Boolean isActive;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public User createUserEntityFromDomain(UserDomain userDomain) {
        completeUserInfo(userDomain);
        return getUserEntity(userDomain);
    }

    @Override
    public UserDomain createUserDomainFromEntity(User user) {
        return getUserDomain(user);
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
        //TODO: Encriptar la pass, y siempre tener el manejo y comparación encriptada
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

    private UserDomain getUserDomain(User user){

        UserDomain userDomain = new UserDomain();

        userDomain.setId(user.getId());
        userDomain.setActive(user.getActive());
        userDomain.setCreated(user.getCreated());
        userDomain.setToken(user.getToken());
        userDomain.setName(user.getName());
        userDomain.setEmail(user.getEmail());
        userDomain.setPassword(user.getPassword());
        userDomain.setLastModified(user.getLastModified());
        userDomain.setLastLogin(user.getLastLogin());

        List<PhoneDomain> phoneDomains = user.getPhones().stream().map(this::getPhoneDomain).toList();
        userDomain.setPhones(phoneDomains);

        return userDomain;
    }

    private Phone getPhoneEntity(PhoneDomain phoneDomain, User userEntity){
        Phone phoneEntity = new Phone();

        phoneEntity.setUser(userEntity);
        phoneEntity.setCityCode(phoneDomain.getCityCode());
        phoneEntity.setCountryCode(phoneDomain.getCountryCode());
        phoneEntity.setNumber(phoneDomain.getNumber());

        return phoneEntity;
    }

    private PhoneDomain getPhoneDomain(Phone phone) {
        PhoneDomain phoneDomain = new PhoneDomain();
        phoneDomain.setCityCode(phone.getCityCode());
        phoneDomain.setNumber(phone.getNumber());
        phoneDomain.setCountryCode(phone.getCountryCode());
        return phoneDomain;
    }

    private String getToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
