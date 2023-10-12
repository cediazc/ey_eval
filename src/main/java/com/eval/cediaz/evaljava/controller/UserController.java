package com.eval.cediaz.evaljava.controller;

import com.eval.cediaz.evaljava.business.UserService;
import com.eval.cediaz.evaljava.domain.UserDomain;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/")
    public ResponseEntity<UserDomain> createUser(@Valid @NotNull(message = "Debe ingresar el body de la request")
                                                     @RequestBody UserDomain userDomain) throws RuntimeException {

        userService.registerUser(userDomain);
        System.out.println("despues " + userDomain);
        return ResponseEntity.ok(userDomain);
    }

    @PutMapping("/{id}")
    public String modifyUser(@RequestBody UserDomain userDomain) {
        return null;
    }
}
