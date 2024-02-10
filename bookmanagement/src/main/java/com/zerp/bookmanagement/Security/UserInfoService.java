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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        User user = repository.findByuserName(username); 
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserInfoDetails(user);
    } 
	

} 
