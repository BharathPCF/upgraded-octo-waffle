package com.bharath.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bharath.model.security.User;
@EnableJpaRepositories
@Repository("userRepository1")
public interface UserRepository1 extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}