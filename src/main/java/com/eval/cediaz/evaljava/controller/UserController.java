package com.eval.cediaz.evaljava.controller;

import com.eval.cediaz.evaljava.business.UserService;
import com.eval.cediaz.evaljava.domain.UserDomain;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userDomain);
    }

    @GetMapping("/uuid")
    public ResponseEntity<UserDomain> getUserByUUID(@RequestParam String uuid) {
        return ResponseEntity.ok(userService.getByUUID(uuid));
    }

    @GetMapping("/email")
    public ResponseEntity<UserDomain> getUserByMail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @DeleteMapping("/uuid")
    public void deleteUserByUUID(@RequestParam String uuid) {
        userService.deleteByUUID(uuid);
    }

    /*TODO: Crear métodos:
     - PUT/updateUser
     */

}
