package com.zerp.bookmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zerp.bookmanagement.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByuserName(String name);

    public Optional<User> findByUserId(Long userId);
    

    
    
}
