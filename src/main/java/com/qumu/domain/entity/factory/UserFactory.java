package com.qumu.domain.entity.factory;

import com.qumu.domain.entity.UserEntity;
import com.qumu.domain.exceptions.UnableToLoadUserException;
import com.qumu.model.UserDTO;

import java.util.List;

/**
 * This is class is responsible to obtain any of UserEntity instances
 */
public interface UserFactory {

    /**
     *
     * Retrieves new instance of UserEntity
     *
     * @param userToCreate datta
     * @return new instance of UserEntity
     */
    UserEntity make(UserDTO userToCreate);

    /**
     *
     * Retrieves instance of UserEntity for given name
     *
     * @param name of user to search
     * @return matched user
     * @throws UnableToLoadUserException
     */
    UserEntity load(String name) throws UnableToLoadUserException;

    /**
     *
     * Retrieves list with all users
     *
     * @return list with all users
     */
    List<UserEntity> loadAll();
}
