package com.qumu.domain.entity.factory.impl;

import com.qumu.data.dao.UserDAO;
import com.qumu.domain.entity.UserEntity;
import com.qumu.domain.entity.factory.UserFactory;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryImplTest {

    private PodamFactory podamFactory;

    private UserFactory userFactory;

    @Mock
    private UserDAO userDAO;

    @PodamCollection
    private List<UserEntity> userEntityList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        podamFactory = new PodamFactoryImpl();
        userFactory = new UserFactoryImpl(userDAO);
        when(userDAO.findByName(anyString())).thenReturn(podamFactory.manufacturePojo(UserEntity.class));
        when(userDAO.findAll()).thenReturn(userEntityList);

    }

    @Test
    public void makeNull() throws Exception {
        Assert.assertNull(userFactory.make(null));
    }

    @Test
    public void make() throws Exception {
        UserDTO userDTO = podamFactory.manufacturePojo(UserDTO.class);
        UserEntity userEntity = userFactory.make(userDTO);
        Assert.assertNotNull(userEntity);
    }

    @Test
    public void loadNull() throws Exception {
        Assert.assertNull(userFactory.load(null));
    }

    @Test
    public void load() throws Exception {
        UserEntity userEntity = userFactory.load("test");
        Assert.assertNotNull(userEntity);
    }

    @Test
    public void loadAll() throws Exception {
        List<UserEntity> userEntityList = userFactory.loadAll();
        Assert.assertNotNull(userEntityList);
    }

}