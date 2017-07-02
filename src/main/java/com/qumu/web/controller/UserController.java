package com.qumu.web.controller;

import com.qumu.domain.api.UserService;
import com.qumu.domain.exceptions.UnableToCreateUserException;
import com.qumu.domain.exceptions.UnableToDeleteUserException;
import com.qumu.domain.exceptions.UnableToLoadUserException;
import com.qumu.domain.exceptions.UnableToUpdateUserException;
import com.qumu.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * User RestFull API
 *
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * Create new user
     *
     * @param userToCreate data
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity create(@Valid UserDTO userToCreate) {
        try {
            userService.create(userToCreate);
        } catch (UnableToCreateUserException e) {
            LOGGER.error("Error creating user", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     *
     * Update user
     *
     * @param userToUpdate data
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = "application/json")
    ResponseEntity<UserDTO> update(@Valid UserDTO userToUpdate) {
        try {
            userService.update(userToUpdate);
        } catch (UnableToUpdateUserException e) {
            LOGGER.error("Error updating user", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     *
     * Delete user
     *
     * @param name of user to delete
     *
     */
    @RequestMapping(value = "/users/{name}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("name") @Valid String name) {
        try {
            userService.delete(name);
        } catch (UnableToDeleteUserException e) {
            LOGGER.error("Error deleting user", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * Get all users
     *
     * @return all users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<UserDTO>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (UnableToLoadUserException e) {
            LOGGER.error("Error loading users", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
