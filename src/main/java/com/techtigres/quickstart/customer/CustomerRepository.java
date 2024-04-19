package com.techtigres.quickstart.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//types: student object and ID as long
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT s FROM Customer s WHERE s.email = ?1")
    Optional<Customer> findByEmail(String email);
}
