package com.eval.cediaz.evaljava.business;

import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.entity.User;
import com.eval.cediaz.evaljava.exception.UserException;

public interface UserService {

    public abstract UserDomain registerUser(UserDomain userDomain);

    UserDomain getByUUID(String uuid);

    UserDomain getByEmail(String email);
}
