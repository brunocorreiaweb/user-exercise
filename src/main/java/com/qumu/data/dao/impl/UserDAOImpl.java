package com.qumu.data.dao.impl;

import com.qumu.data.dao.UserDAO;
import com.qumu.data.entity.User;
import com.qumu.data.exceptions.UserDAOException;
import com.qumu.data.mapper.UserDaoMapper;
import com.qumu.data.repository.UserRepository;
import com.qumu.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDAOImpl implements UserDAO {

    private final DataSource dataSource;
    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(DataSource dataSource, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByName(String name) throws UserDAOException {
        if (Objects.isNull(name)) {
            return null;
        }

        User user = userRepository.findOne(name);

        if (Objects.isNull(user)) {
            throw new UserDAOException("Unable to find User in BD");
        }

        return UserDaoMapper.mapUserToUserEntity(user, this);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        Iterable<User> userIterable = userRepository.findAll();
        List<UserEntity> userEntities = new ArrayList<>();
        userIterable.forEach(user -> userEntities.add(UserDaoMapper.mapUserToUserEntity(user, this)));
        return userEntities;
    }

    @Override
    @Transactional
    public void save(UserEntity userEntity) throws UserDAOException {
        if (Objects.isNull(userEntity) || Objects.isNull(userEntity.getName())) {
            throw new UserDAOException("User data is null");
        }
        User user = new User();
        user.setName(userEntity.getName());
        user.setDob(userEntity.getDateOfBirth());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(UserEntity userEntity) throws UserDAOException {
        if (Objects.isNull(userEntity) || Objects.isNull(userEntity.getName())) {
            throw new UserDAOException("User data is null");
        }
        User user = userRepository.findOne(userEntity.getName());
        if (Objects.isNull(user)) {
            throw new UserDAOException("Unable to find User in BD");
        }
        userRepository.delete(user);
    }
}
