package com.qumu.data.dao.impl;

import com.qumu.data.dao.UserDAO;
import com.qumu.data.entity.User;
import com.qumu.data.exceptions.UserDAOException;
import com.qumu.data.repository.UserRepository;
import com.qumu.domain.entity.UserEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamCollection;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOImplTest {

    private UserDAO userDAO;

    private PodamFactory podamFactory;

    private UserEntity userEntity;

    @PodamCollection
    private List<User> userList = new ArrayList<>();

    @Mock
    private DataSource dataSource;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        podamFactory = new PodamFactoryImpl();
        userDAO = new UserDAOImpl(dataSource, userRepository);
        when(userRepository.findOne("test")).thenReturn(podamFactory.manufacturePojo(User.class));
        when(userRepository.findAll()).thenReturn(userList);
        userEntity = podamFactory.manufacturePojo(UserEntity.class);
    }

    @Test
    public void findByNameNull() throws Exception {
        Assert.assertNull(userDAO.findByName(null));
    }

    @Test(expected = UserDAOException.class)
    public void findByNameError() throws Exception {
        userDAO.findByName("");
    }

    @Test
    public void findByName() throws Exception {
        Assert.assertNotNull(userDAO.findByName("test"));
    }

    @Test
    public void findAll() throws Exception {
        Assert.assertNotNull(userDAO.findAll());
    }

    @Test(expected = UserDAOException.class)
    public void saveNull() throws Exception {
        userDAO.save(null);
    }

    @Test
    public void saveError() throws Exception {
        userDAO.save(userEntity);
    }

    @Test(expected = UserDAOException.class)
    public void deleteNull() throws Exception {
        userDAO.delete(null);
    }

    @Test(expected = UserDAOException.class)
    public void deleteError() throws Exception {
        userDAO.delete(userEntity);
    }

    @Test
    public void delete() throws Exception {
        userEntity.setName("test");
        userDAO.delete(userEntity);
    }


}