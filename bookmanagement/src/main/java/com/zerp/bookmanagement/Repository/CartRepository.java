package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // You can add custom queries or methods specific to Carts if needed
}

