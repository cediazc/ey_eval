package com.eval.cediaz.evaljava.business;

import com.eval.cediaz.evaljava.domain.UserDomain;

public interface UserService {

    UserDomain registerUser(UserDomain userDomain);

    UserDomain getByUUID(String uuid);

    UserDomain getByEmail(String email);

    void deleteByUUID(String uuid);

    UserDomain updateUser(UserDomain userDomain);
}
