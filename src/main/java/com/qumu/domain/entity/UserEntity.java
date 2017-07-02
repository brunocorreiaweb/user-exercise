package com.qumu.domain.entity;

import com.qumu.data.dao.UserDAO;
import com.qumu.data.dao.impl.UserDAOImpl;
import com.qumu.data.exceptions.UserDAOException;
import com.qumu.domain.exceptions.UnableToCreateOrUpdateUserException;
import com.qumu.domain.exceptions.UnableToDeleteUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this class represent the Domain Entity "UserDTO"
 */
public class UserEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);

    private UserDAO userDAO;
    private String name;
    private long dateOfBirth;

    public UserEntity(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    /**
     *
     * Save this instance of User
     *
     * @throws UnableToCreateOrUpdateUserException
     */
    public void save() throws UnableToCreateOrUpdateUserException {
        try {
            userDAO.save(this);
        } catch (UserDAOException e) {
            LOGGER.error("Error saving user", e);
            throw new UnableToCreateOrUpdateUserException("Error saving user");
        }
    }

    /**
     *
     * Deletes this instance of User
     *
     * @throws UnableToDeleteUserException
     */
    public void delete() throws UnableToDeleteUserException {
        try {
            userDAO.delete(this);
        } catch (UserDAOException e) {
            LOGGER.error("Error deleting user", e);
            throw new UnableToDeleteUserException("Error deleting user");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }
}
