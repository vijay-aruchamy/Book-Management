package com.zerp.bookmanagement.Security;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.UserRepository;



@Service
public class UserInfoService implements UserDetailsService { 

	@Autowired
	private UserRepository repository; 

	

	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
        User user = repository.findByEmail(email); 
        if (user == null) {
            throw new UsernameNotFoundException("Email not found with username: " + email);
        }
        return new UserInfoDetails(user);
    } 
	

} 
