package com.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
      Role findById(int roleId);
}
