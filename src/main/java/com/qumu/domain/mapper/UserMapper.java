package com.qumu.domain.mapper;

import com.qumu.domain.entity.UserEntity;
import com.qumu.model.UserDTO;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User DTO to Domain User Entity Mapper
 */
public class UserMapper {
    public static void mapUserIntoEntityInstance(UserDTO user, UserEntity userEntity) {
        if(Objects.nonNull(userEntity) && Objects.nonNull(user)){
            userEntity.setName(user.getName());
            userEntity.setDateOfBirth(user.getDateOfBirthMillis());
        }
    }

    public static List<UserDTO> mapUserEntityListToUserList(List<UserEntity> userEntities) {
        if(Objects.isNull(userEntities) || userEntities.isEmpty()){
            return Collections.emptyList();
        }
        return userEntities.stream().map(UserMapper::mapEntityToUser).collect(Collectors.toList());
    }

    public static UserDTO mapEntityToUser(UserEntity userEntity) {
        if(Objects.isNull(userEntity)){
            return null;
        }
        UserDTO user = new UserDTO();
        user.setName(userEntity.getName());
        user.setDateOfBirthMillis(userEntity.getDateOfBirth());
        return user;
    }
}
