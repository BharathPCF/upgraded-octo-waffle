package com.bharath.repository;


import org.springframework.data.repository.Repository;
import com.bharath.model.security.User;


public interface UserRepository extends Repository<User, Long> {
    User findByUsername(String username);
    
	
   // User findById(@Param("id") Long Id);
	
    
  //@Query("SELECT USER FROM User  WHERE user.id =:id")
 // @Transactional(readOnly = true)
//   User findById(@Param("id") Long id);
    
   public  void save(User user);


User findById(Long userId);


    
}
