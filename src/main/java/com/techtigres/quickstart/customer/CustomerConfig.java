package com.techtigres.quickstart.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository){
        return args ->{
           Customer Nicky = new Customer(
                    "Nicky",
                    LocalDate.of(2000, Month.DECEMBER,5),
                    "ninuMaliante@gmail.com"
            );
            Customer Ivan = new Customer(
                    3434,
                    "Ivan",
                    LocalDate.of(1990, Month.DECEMBER,5),
                    "ninuMaliante@gmail.com"
            );

            repository.saveAll(List.of(Nicky, Ivan)
            );
        };
    }
}
