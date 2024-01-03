package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can add custom queries or methods specific to Orders if needed
}

