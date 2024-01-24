package com.zerp.bookmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.Model.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);
    // You can add custom queries or methods specific to Addresses if needed
}

