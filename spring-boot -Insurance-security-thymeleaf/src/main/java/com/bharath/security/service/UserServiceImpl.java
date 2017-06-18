package com.bharath.security.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bharath.model.security.User;
import com.bharath.repository.UserRepository1;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository1 userRepository1;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository1.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setActive(1);
       //Authority userAuthority = roleRepository.findByName("ADMIN");
       //user.setAuthorities(new HashSet<Authority>(Arrays.asList(userAuthority)));
		userRepository1.save(user);
	}

}
