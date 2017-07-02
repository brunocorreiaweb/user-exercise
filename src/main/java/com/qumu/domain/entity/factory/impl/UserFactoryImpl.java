package com.qumu.domain.entity.factory.impl;

import com.qumu.data.dao.UserDAO;
import com.qumu.data.exceptions.UserDAOException;
import com.qumu.domain.entity.UserEntity;
import com.qumu.domain.entity.factory.UserFactory;
import com.qumu.domain.exceptions.UnableToLoadUserException;
import com.qumu.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by Bruno Correia on 02/07/2017.
 */
@Component
public class UserFactoryImpl implements UserFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFactoryImpl.class);

    private final UserDAO userDAO;

    @Autowired
    public UserFactoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserEntity make(UserDTO userToCreate) {

        if (Objects.isNull(userToCreate)) {
            return null;
        }

        UserEntity userEntity = new UserEntity(userDAO);
        userEntity.setName(userToCreate.getName());
        userEntity.setDateOfBirth(userToCreate.getDateOfBirthMillis());

        return userEntity;
    }

    @Override
    public UserEntity load(String name) throws UnableToLoadUserException {
        if (Objects.isNull(name)) {
            return null;
        }

        try {
            return userDAO.findByName(name);
        } catch (UserDAOException e) {
            LOGGER.error("Error loading user", e);
            throw new UnableToLoadUserException("Error loading user");
        }
    }

    @Override
    public List<UserEntity> loadAll() {
        return userDAO.findAll();
    }
}
