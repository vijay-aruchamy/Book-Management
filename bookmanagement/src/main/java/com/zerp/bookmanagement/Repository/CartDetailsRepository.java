package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.CartDetails;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
}

    

    

