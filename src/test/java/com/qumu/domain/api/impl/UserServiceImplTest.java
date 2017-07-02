package com.qumu.domain.api.impl;

import com.qumu.domain.api.UserService;
import com.qumu.domain.entity.UserEntity;
import com.qumu.domain.entity.factory.UserFactory;
import com.qumu.domain.exceptions.UnableToCreateUserException;
import com.qumu.domain.exceptions.UnableToDeleteUserException;
import com.qumu.domain.exceptions.UnableToUpdateUserException;
import com.qumu.model.UserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamCollection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserService userService;

    private PodamFactory podamFactory;
    private UserDTO userDTO;

    @PodamCollection
    List<UserEntity> userEntities = new ArrayList<>();

    @Mock
    private UserFactory userFactory;

    @Mock
    private UserEntity userEntity;

    @Before
    public void setUp() throws Exception {
        podamFactory = new PodamFactoryImpl();
        userService = new UserServiceImpl(userFactory);
        when(userFactory.make(any())).thenReturn(userEntity);
        when(userFactory.load("test")).thenReturn(userEntity);
        when(userFactory.loadAll()).thenReturn(userEntities);
        userDTO = podamFactory.manufacturePojo(UserDTO.class);
    }

    @Test(expected = UnableToCreateUserException.class)
    public void createNull() throws Exception {
        userService.create(null);
    }

    @Test
    public void create() throws Exception {
        userService.create(userDTO);
    }

    @Test(expected = UnableToUpdateUserException.class)
    public void updateNull() throws Exception {
        userService.update(null);
    }

    @Test(expected = UnableToUpdateUserException.class)
    public void updateError() throws Exception {
        userService.update(userDTO);
    }

    @Test
    public void update() throws Exception {
        userDTO.setName("test");
        userService.update(userDTO);
    }

    @Test(expected = UnableToDeleteUserException.class)
    public void deleteNull() throws Exception {
        userService.delete(null);
    }

    @Test(expected = UnableToDeleteUserException.class)
    public void deleteError() throws Exception {
        userService.delete("");
    }

    @Test
    public void delete() throws Exception {
        userService.delete("test");
    }

    @Test
    public void getAll() throws Exception {
        List<UserDTO> users = userService.getAll();
        Assert.assertNotNull(users);
    }

}