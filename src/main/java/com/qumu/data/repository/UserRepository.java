package com.qumu.data.repository;

import com.qumu.data.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * User Crud Repository
 *
 */
public interface UserRepository extends CrudRepository<User, String> {

}
