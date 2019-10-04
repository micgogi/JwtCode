package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.model.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	 Users findByUserName(String userName);
     Boolean existsByUserName(String userName);
     Boolean existsByUserEmail(String userEmail);
     @Query(value="select * from users where user_name like %:name% and status=1",nativeQuery=true)
     List<Users> searchUsersByUsername(@Param("name") String name);

}
