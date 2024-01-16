package com.jsp.ums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.ums.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("From User u where u.userName LIKE %?1%")
	List<User> findByName(String UserName);

}
