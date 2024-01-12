package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartIdByuser(User user);
    // You can add custom queries or methods specific to Carts if needed

}

  
