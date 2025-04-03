package com.javaweb.repository;

import com.javaweb.entity.UserEntity;
import com.javaweb.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity , Long > {

List<UserRoleEntity> findUserRoleEntitiesByUser (UserEntity user);

}
