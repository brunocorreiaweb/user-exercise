package com.qumu.domain.api.impl;

import com.qumu.domain.api.UserService;
import com.qumu.domain.entity.UserEntity;
import com.qumu.domain.entity.factory.UserFactory;
import com.qumu.domain.exceptions.*;
import com.qumu.domain.mapper.UserMapper;
import com.qumu.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by Bruno Correia on 02/07/2017.
 */
@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserFactory userFactory;

    @Autowired
    public UserServiceImpl(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public void create(UserDTO userToCreate) throws UnableToCreateUserException {

        if (Objects.isNull(userToCreate)) {
            throw new UnableToCreateUserException("UserDTO data is null");
        }

        UserEntity userEntity = userFactory.make(userToCreate);
        try {
            userEntity.save();
        } catch (UnableToCreateOrUpdateUserException e) {
            LOGGER.error("Error creating user", e);
            throw new UnableToCreateUserException("Error creating user");
        }

    }

    @Override
    public void update(UserDTO userToUpdate) throws UnableToUpdateUserException {
        if (Objects.isNull(userToUpdate) || StringUtils.isEmpty(userToUpdate.getName())) {
            throw new UnableToUpdateUserException("UserDTO data is null");
        }

        try {
            UserEntity userEntity = userFactory.load(userToUpdate.getName());
            if(Objects.isNull(userEntity)){
                throw new UnableToUpdateUserException("Error finding user to update");
            }
            UserMapper.mapUserIntoEntityInstance(userToUpdate, userEntity);
            userEntity.save();
        } catch (UnableToLoadUserException | UnableToCreateOrUpdateUserException e) {
            LOGGER.error("Error updating user", e);
            throw new UnableToUpdateUserException("Error updating user");
        }
    }

    @Override
    public void delete(String name) throws UnableToDeleteUserException {
        if (StringUtils.isEmpty(name)) {
            throw new UnableToDeleteUserException("UserDTO name is null");
        }

        try {
            UserEntity userEntity = userFactory.load(name);
            if(Objects.isNull(userEntity)){
                throw new UnableToDeleteUserException("Error finding user to delete");
            }
            userEntity.delete();
        } catch (UnableToLoadUserException e) {
            LOGGER.error("Error deleting user", e);
            throw new UnableToDeleteUserException("Error deleting user");
        }

    }

    @Override
    public List<UserDTO> getAll() throws UnableToLoadUserException {
        return UserMapper.mapUserEntityListToUserList(userFactory.loadAll());
    }
}
