package com.example.demo;

import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, BusinessRepository businessRepository, VisitRepository visitRepository) {

        return args -> {
            log.info("Preloading " + customerRepository.save(new CustomerEntity("Bilbo", "Baggins", "burglar")));
            log.info("Preloading " + customerRepository.save(new CustomerEntity("Frodo", "Baggins", "thief")));
            //log.info("Preloading " + customerRepository.save(new CustomerEntity("Frodo", "Baggins", "thief")));

            customerRepository.findAll().forEach(customerEntity -> log.info("Preloaded " + customerEntity));


            businessRepository.save(new BusinessEntity("Mcdo", "mcdo@lol.com"));
            businessRepository.save(new BusinessEntity("Netfilx", "nosupport@Netflix.com"));

            businessRepository.findAll().forEach(businessEntity -> log.info("Preloaded " + businessEntity));
        };
    }

}
