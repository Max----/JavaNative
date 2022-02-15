package com.demo.service;

import com.demo.NotFoundException;
import com.demo.entity.BusinessEntity;
import com.demo.entity.CustomerEntity;
import com.demo.entity.LoyaltyEntity;
import com.demo.entity.VisitEntity;
import com.demo.repository.BusinessRepository;
import com.demo.repository.CustomerRepository;
import com.demo.repository.VisitRepository;
import com.demo.mapper.CustomerMapper;
import com.demo.mapper.LoyaltyMapper;
import com.demo.mapper.VisitMapper;
import com.demo.repository.LoyaltyRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.openapitools.api.CustomerApi;
import org.openapitools.model.Customer;
import org.openapitools.model.Loyalty;
import org.openapitools.model.Visit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/customer")
@ExecuteOn(TaskExecutors.IO)
public class CustomerResource implements CustomerApi {

    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;
    private final VisitRepository visitRepository;
    private final LoyaltyRepository loyaltyRepository;

    private CustomerMapper customerMapper;
    private LoyaltyMapper loyaltyMapper;
    private VisitMapper visitMapper;

    public CustomerResource(CustomerRepository customerRepository, BusinessRepository businessRepository, VisitRepository visitRepository, LoyaltyRepository loyaltyRepository,
                            CustomerMapper customerMapper, LoyaltyMapper loyaltyMapper, VisitMapper visitMapper){
        this.businessRepository = businessRepository;
        this.customerRepository = customerRepository;
        this.visitRepository = visitRepository;
        this.loyaltyRepository = loyaltyRepository;
        this.customerMapper = customerMapper;
        this.loyaltyMapper = loyaltyMapper;
        this.visitMapper = visitMapper;
    }

    @Override
    public Customer createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customerRepository.save(customer);
        return customerMapper.toApi(customer);
    }

    @Override
    public Customer getCustomerMetadatas(@PathVariable Integer id) {
        CustomerEntity customer = customerRepository.findById((long)id);
        if (customer == null) {
            throw new NotFoundException();
        }
        return customerMapper.toApi(customer);
    }

    @Override
    public Customer updateCustomerMetadatas(@PathVariable Integer id, Customer customer) {
        CustomerEntity customerEntity = customerRepository.findById((long)id);
        if (customerEntity == null) {
            throw new NotFoundException();
        }

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        return customerMapper.toApi(customerEntity);
    }

    @Override
    public void deleteCustomer(@PathVariable Integer id) {
        CustomerEntity customerEntity = customerRepository.findById((long)id);
        if (customerEntity == null) {
            throw new NotFoundException();
        }
        customerRepository.delete(customerEntity);
    }

    @Override
    public Loyalty getCustomerloyalty(@PathVariable Integer id, @PathVariable Integer businessId) {

        LoyaltyEntity loyaltyEntity = loyaltyRepository.findByLoyalty(id,businessId);
        if (loyaltyEntity == null) {
            throw new NotFoundException();
        }

        return loyaltyMapper.toApi(loyaltyEntity);
    }

    @Override
    public Visit addVisit(@PathVariable Integer id, @PathVariable Integer businessId) {
        CustomerEntity customerEntity = customerRepository.findById((long)id);
        if (customerEntity == null) {
            throw new NotFoundException();
        }
        BusinessEntity businessEntity = businessRepository.findById((long)businessId);
        if (businessEntity == null) {
            throw new NotFoundException();
        }
        VisitEntity newVisit = new VisitEntity(id, businessId);
        visitRepository.save(newVisit);

        incrementLoyalty(id, businessId);
        return visitMapper.toApi(newVisit);
    }

    @GET
    @Path("/all")
    @Produces({ "application/json" })
    public List<Customer> allCustomer(){
        List<Customer> customers = new ArrayList<>();
        customers = customerRepository.findAll().stream().map(cus -> customerMapper.toApi((CustomerEntity) cus)).collect(Collectors.toList());
        return customers;
    }

    @GET
    @Path("/visits/all")
    @Produces({ "application/json" })
    public List<Visit> allVisits(){
        List<Visit> visits = new ArrayList<>();
        visits = visitRepository.findAll().stream().map(cus -> visitMapper.toApi((VisitEntity) cus)).collect(Collectors.toList());
        return visits;
    }

    @GET
    @Path("/loyalty/all")
    @Produces({ "application/json" })
    public List<Loyalty> allLoyalties(){
        return loyaltyRepository.findAll().stream().map(loy -> loyaltyMapper.toApi(loy)).collect(Collectors.toList());
    }

    private void incrementLoyalty(Integer id, Integer businessId){
        LoyaltyEntity loyaltyEntity = loyaltyRepository.findByLoyalty(id, businessId);
        if (loyaltyEntity == null) {
            LoyaltyEntity newLoyaltyEntity = new LoyaltyEntity(id, businessId);
            loyaltyRepository.save(newLoyaltyEntity);
        }else{
            loyaltyEntity.incrementLoyalty();
            loyaltyRepository.update(loyaltyEntity);
        }
    }
}




