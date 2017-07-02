package com.qumu.domain.exceptions;

/**
 * Created by Bruno Correia on 02/07/2017.
 */
public class UnableToCreateOrUpdateUserException extends Exception {
    public UnableToCreateOrUpdateUserException(String errorMessage) {
        super(errorMessage);
    }
}
