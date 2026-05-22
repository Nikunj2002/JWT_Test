package com.JWTTest.JWT.TEST.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JWTTest.JWT.TEST.Model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{
	Optional<Users> findByUsername(String username);
}
