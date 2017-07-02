package com.qumu.domain.entity;

import com.qumu.data.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by Bruno Correia on 02/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserEntityTest {

    private UserEntity userEntity;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userEntity = new UserEntity(userDAO);
    }

    @Test
    public void save() throws Exception {
        userEntity.save();
    }

    @Test
    public void delete() throws Exception {
        userEntity.delete();
    }

}