package com.eval.cediaz.evaljava.repository;

import com.eval.cediaz.evaljava.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findByUserId(String userId);

}
