package com.eval.cediaz.evaljava.repository;

import com.eval.cediaz.evaljava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
