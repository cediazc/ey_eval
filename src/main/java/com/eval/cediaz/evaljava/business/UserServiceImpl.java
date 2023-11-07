package com.eval.cediaz.evaljava.business;

import com.eval.cediaz.evaljava.domain.PhoneDomain;
import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.entity.Phone;
import com.eval.cediaz.evaljava.entity.User;
import com.eval.cediaz.evaljava.exception.UserException;
import com.eval.cediaz.evaljava.exception.UserNotFoundException;
import com.eval.cediaz.evaljava.mapper.UserMapperService;
import com.eval.cediaz.evaljava.repository.PhoneRepository;
import com.eval.cediaz.evaljava.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UserMapperService userMapperService;


    @Override
    @Transactional
    public UserDomain registerUser(UserDomain userDomain) {

        // Se busca en la BD si ya está registrado el correo
        User validateUserEntity = userRepository.findByEmail(userDomain.getEmail());

        if(validateUserEntity!= null){
            throw new UserException("El correo ya está registrado anteriormente");
        }
        User userEntity = userMapperService.createUserEntityFromDomain(userDomain);

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

    @Override
    public UserDomain getByUUID(String uuid) {
        User user = userRepository.findById(uuid);

        if(user == null){
            throw new UserNotFoundException("UUID no existe en la BD");
        }

        return userMapperService.createUserDomainFromEntity(user);
    }

    @Override
    public UserDomain getByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("Email no existe en la BD");
        }

        return userMapperService.createUserDomainFromEntity(user);
    }


}
