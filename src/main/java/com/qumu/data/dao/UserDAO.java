package com.qumu.data.dao;

import com.qumu.data.exceptions.UserDAOException;
import com.qumu.domain.entity.UserEntity;

import java.util.List;

/**
 * This class is responsible for the Data Access Layer
 */
public interface UserDAO {
    /**
     *
     * Find user in database by name
     *
     * @param name of user
     * @return user instance
     * @throws UserDAOException
     */
    UserEntity findByName(String name) throws UserDAOException;

    /**
     *
     * Finds all users in database
     *
     * @return all users in database
     */
    List<UserEntity> findAll();

    /**
     *
     * Persist or update user in database
     *
     * @param userEntity to persist or update
     * @throws UserDAOException
     */
    void save(UserEntity userEntity) throws UserDAOException;

    /**
     *
     * Deletes user from database
     *
     * @param userEntity to delete
     * @throws UserDAOException
     */
    void delete(UserEntity userEntity) throws UserDAOException;
}
