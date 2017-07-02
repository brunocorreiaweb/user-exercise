package com.qumu.domain.api;

import com.qumu.domain.exceptions.UnableToCreateUserException;
import com.qumu.domain.exceptions.UnableToDeleteUserException;
import com.qumu.domain.exceptions.UnableToLoadUserException;
import com.qumu.domain.exceptions.UnableToUpdateUserException;
import com.qumu.model.UserDTO;

import java.util.List;

/**
 * This class is important to separate the business logic from the web api technology.
 * E.G. SOAP WS can use this API or other internal Service classes
 */
public interface UserService {

    /**
     *
     * Create a new User
     *
     * @param userToCreate data
     * @throws UnableToCreateUserException
     */
    void create(UserDTO userToCreate) throws UnableToCreateUserException;

    /**
     *
     * Update existing user
     *
     * @param userToUpdate data
     * @throws UnableToUpdateUserException
     */
    void update(UserDTO userToUpdate) throws UnableToUpdateUserException;

    /**
     *
     * Deletes existing user
     *
     * @param name of user to delete
     * @throws UnableToDeleteUserException
     */
    void delete(String name) throws UnableToDeleteUserException;

    /**
     *
     * List all existing users
     *
     * @return list of all existing users
     * @throws UnableToLoadUserException
     */
    List<UserDTO> getAll() throws UnableToLoadUserException;

}
