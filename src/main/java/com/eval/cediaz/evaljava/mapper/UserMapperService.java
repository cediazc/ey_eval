package com.eval.cediaz.evaljava.mapper;

import com.eval.cediaz.evaljava.domain.UserDomain;
import com.eval.cediaz.evaljava.entity.User;

public interface UserMapperService {

    public User createUserEntityFromDomain(UserDomain userDomain);

    public UserDomain createUserDomainFromEntity(User user);
}
