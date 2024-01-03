package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // You can add custom queries or methods specific to Addresses if needed
}

