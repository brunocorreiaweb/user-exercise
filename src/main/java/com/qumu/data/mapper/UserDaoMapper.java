package com.qumu.data.mapper;

import com.qumu.data.dao.impl.UserDAOImpl;
import com.qumu.data.entity.User;
import com.qumu.domain.entity.UserEntity;

import java.util.Objects;

/**
 * Domain User Entity and Database User Entity mapper
 */
public class UserDaoMapper {

    /**
     *
     * Maps database user to domain user entity
     *
     * @param user from database
     * @param userDAO instance
     * @return domain usar entity instance
     */
    public static UserEntity mapUserToUserEntity(User user, UserDAOImpl userDAO) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserEntity userEntity = new UserEntity(null);
        userEntity.setDateOfBirth(user.getDob());
        userEntity.setName(user.getName());
        userEntity.setUserDAO(userDAO);
        return userEntity;
    }
}
