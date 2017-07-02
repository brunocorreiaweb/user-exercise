package com.qumu.data.mapper;

import com.qumu.data.dao.impl.UserDAOImpl;
import com.qumu.data.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoMapperTest {

    private PodamFactory podamFactory;

    @Mock
    private UserDAOImpl userDAO;

    private User user;

    @Before
    public void setUp() throws Exception {
        podamFactory = new PodamFactoryImpl();
        user = podamFactory.manufacturePojo(User.class);
    }

    @Test
    public void mapUserToUserEntityNull() throws Exception {
        Assert.assertNull(UserDaoMapper.mapUserToUserEntity(null, userDAO));
    }

    @Test
    public void mapUserToUserEntity() throws Exception {
        Assert.assertNotNull(UserDaoMapper.mapUserToUserEntity(user, userDAO));
    }

}