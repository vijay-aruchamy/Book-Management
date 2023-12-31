package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zerp.bookmanagement.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByuserName(String name);

    public User findByUserId(Long userId);
    

    
    
}
