package com.techtigres.quickstart.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//tells java this is a spring bean
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();

    }

    public void addNewCustomer(Customer customer) {

       Optional<Customer> customerByEmail =
               customerRepository.findByEmail(customer.getEmail());

       if (customerByEmail.isPresent()){
           throw new IllegalStateException("email taken");
       }
       customerRepository.save(customer);

        System.out.println(customer);
    }

    public void deleteCustomer(Long customerId) {
       boolean exists = customerRepository.existsById(customerId);

       if(!exists){
           throw new IllegalStateException("Customer with ID" + customerId + " does not exist!");
       }
       customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer with id " + customerId + " does not exist."));
        if (name!= null &&
                name.length() > 0 &&
                !Objects.equals(customer.getName(), name)){
            customer.setName(name);
        }
        if (email!= null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)){
            Optional<Customer> customerOptional = customerRepository.findByEmail(email);
            if(customerOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            customer.setEmail(email);
        }

    }
}
