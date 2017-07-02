package com.qumu.domain.mapper;

import com.qumu.domain.entity.UserEntity;
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
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    @Mock
    private UserDTO userDTO;

    @Mock
    private UserEntity userEntity;

    @PodamCollection()
    private List<UserEntity> userEntityList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void mapUserIntoEntityInstance() throws Exception {
        UserMapper.mapUserIntoEntityInstance(userDTO, userEntity);
    }

    @Test
    public void mapUserIntoEntityInstanceNull() throws Exception {
        UserMapper.mapUserIntoEntityInstance(null, null);
    }

    @Test
    public void mapEntityToUser() throws Exception {
        UserDTO userDTO = UserMapper.mapEntityToUser(userEntity);
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void mapEntityToUserNull() throws Exception {
        UserDTO userDTO = UserMapper.mapEntityToUser(null);
        Assert.assertNull(userDTO);
    }

    @Test
    public void mapUserEntityListToUserList() throws Exception {
        List<UserDTO> list = UserMapper.mapUserEntityListToUserList(userEntityList);
        Assert.assertNotNull(list);
        Assert.assertEquals(userEntityList.size(), list.size());
    }

    @Test
    public void mapUserEntityListToUserListNull() throws Exception {
        List<UserDTO> list = UserMapper.mapUserEntityListToUserList(null);
        Assert.assertNotNull(list);
    }


}