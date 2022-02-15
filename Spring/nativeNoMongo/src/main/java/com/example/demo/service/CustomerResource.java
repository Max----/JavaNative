package com.example.demo.service;

import com.example.demo.NotFoundException;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.LoyaltyEntity;
import com.example.demo.entity.VisitEntity;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.VisitRepository;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.LoyaltyMapper;
import com.example.demo.mapper.VisitMapper;
import com.example.demo.repository.LoyaltyRepository;
import org.openapitools.api.CustomerApi;
import org.openapitools.model.Customer;
import org.openapitools.model.Loyalty;
import org.openapitools.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerResource implements CustomerApi {

    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;
    private final VisitRepository visitRepository;
    private final LoyaltyRepository loyaltyRepository;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private LoyaltyMapper loyaltyMapper;
    @Autowired
    private VisitMapper visitMapper;

    public CustomerResource(CustomerRepository customerRepository, BusinessRepository businessRepository, VisitRepository visitRepository, LoyaltyRepository loyaltyRepository){
        this.businessRepository = businessRepository;
        this.customerRepository = customerRepository;
        this.visitRepository = visitRepository;
        this.loyaltyRepository = loyaltyRepository;
    }

    @Override
    public ResponseEntity<Customer> createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customerRepository.save(customer);
        return new ResponseEntity<Customer>(customerMapper.toApi(customer), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Customer> getCustomerMetadatas(@PathVariable Integer id) {
        CustomerEntity customer = customerRepository.findById((long)id).orElseThrow(() -> new NotFoundException());
        return new ResponseEntity<Customer>(customerMapper.toApi(customer), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> updateCustomerMetadatas(@PathVariable Integer id, @RequestBody Customer customer) {
        CustomerEntity customerEntity = customerRepository.findById((long)id).orElseThrow(() -> new NotFoundException());

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        customerRepository.save(customerEntity);
        return new ResponseEntity<Customer>(customerMapper.toApi(customerEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        CustomerEntity customerEntity = customerRepository.findById((long)id).orElseThrow(() -> new NotFoundException());
        customerRepository.delete(customerEntity);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Loyalty> getCustomerloyalty(@PathVariable Integer id, @PathVariable Integer businessId) {

        LoyaltyEntity loyaltyEntity = loyaltyRepository.findByCustomerIdAndBusinessId(Long.valueOf(id), Long.valueOf(businessId));
        if (loyaltyEntity == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<Loyalty>(loyaltyMapper.toApi(loyaltyEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Visit> addVisit(@PathVariable Integer id, @PathVariable Integer businessId) {
        CustomerEntity customerEntity = customerRepository.findById((long)id).orElseThrow(() -> new NotFoundException());
        BusinessEntity businessEntity = businessRepository.findById((long)businessId).orElseThrow(() -> new NotFoundException());
        VisitEntity newVisit = new VisitEntity(id, businessId);
        visitRepository.save(newVisit);

        incrementLoyalty(id, businessId);
        return new ResponseEntity<Visit>(visitMapper.toApi(newVisit), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/all",
            produces = { "application/json" }
    )
    public List<Customer> allCustomer(){
        List<Customer> customers = new ArrayList<>();
        customers = customerRepository.findAll().stream().map(cus -> customerMapper.toApi((CustomerEntity) cus)).collect(Collectors.toList());
        return customers;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/visits/all",
            produces = { "application/json" }
    )
    public List<Visit> allVisits(){
        List<Visit> visits = new ArrayList<>();
        visits = visitRepository.findAll().stream().map(cus -> visitMapper.toApi((VisitEntity) cus)).collect(Collectors.toList());
        return visits;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/loyalty/all",
            produces = { "application/json" }
    )
    public List<Loyalty> allLoyalties(){
        List<Loyalty> loyalties = new ArrayList<>();
        loyalties = loyaltyRepository.findAll().stream().map(cus -> loyaltyMapper.toApi((LoyaltyEntity) cus)).collect(Collectors.toList());
        return loyalties;
    }

    private void incrementLoyalty(Integer id, Integer businessId){
        LoyaltyEntity loyaltyEntity = loyaltyRepository.findByCustomerIdAndBusinessId(Long.valueOf(id), Long.valueOf(businessId));
        if (loyaltyEntity == null) {
            LoyaltyEntity newLoyaltyEntity = new LoyaltyEntity(id, businessId);
            loyaltyRepository.save(newLoyaltyEntity);
        }else{
            loyaltyEntity.incrementLoyalty();
            loyaltyRepository.save(loyaltyEntity);
        }
    }
}




