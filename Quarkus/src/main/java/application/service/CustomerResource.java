package application.service;

import application.entity.BusinessEntity;
import application.entity.CustomerEntity;
import application.entity.LoyaltyEntity;
import application.entity.VisitEntity;
import application.mapper.CustomerMapper;
import application.mapper.LoyaltyMapper;
import application.mapper.VisitMapper;
import org.openapitools.api.CustomerApi;
import org.openapitools.model.Customer;
import org.openapitools.model.Loyalty;
import org.openapitools.model.Visit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("customer")
@ApplicationScoped
public class CustomerResource implements CustomerApi {

    @Inject
    CustomerMapper customerMapper;
    @Inject
    LoyaltyMapper loyaltyMapper;
    @Inject
    VisitMapper visitMapper;


    @Transactional
    @Override
    public Customer createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.persist();
        return customerMapper.toApi(customer);
    }

    @Override
    public Customer getCustomerMetadatas(Integer id) {
        CustomerEntity c = CustomerEntity.findById((long)id);
        if (c == null) {
            throw new NotFoundException();
        }
        return customerMapper.toApi(c);
    }

    @Transactional
    @Override
    public Customer updateCustomerMetadatas(Integer id, Customer customer) {
        CustomerEntity entity = CustomerEntity.findById((long)id);
        if (entity == null) {
            throw new NotFoundException();
        }

        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        return customerMapper.toApi(entity);
    }

    @Override
    public void deleteCustomer(Integer id) {
        CustomerEntity entity = CustomerEntity.findById((long)id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @Override
    public Loyalty getCustomerLoyalty(Integer id, Integer businessId) {
        LoyaltyEntity loyaltyEntity = LoyaltyEntity.findLoyalty(id, businessId);
        if (loyaltyEntity == null) {
            throw new NotFoundException();
        }

        return loyaltyMapper.toApi(loyaltyEntity);
    }

    @Transactional
    @Override
    public Visit addVisit(Integer id, Integer businessId) {
        CustomerEntity customerEntity = CustomerEntity.findById((long)id);
        if (customerEntity == null) {
            throw new NotFoundException();
        }
        BusinessEntity businessEntity = BusinessEntity.findById((long)businessId);
        if (businessEntity == null) {
            throw new NotFoundException();
        }
        VisitEntity newVisit = new VisitEntity(id, businessId);
        newVisit.persist();

        incrementLoyalty(id, businessId);
        return visitMapper.toApi(newVisit);
    }

    @GET
    @Path("/all")
    @Produces({ "application/json" })
    public List<Customer> allCustomer(){
        List<Customer> customers = new ArrayList<>();
        customers = CustomerEntity.findAll().stream().map(cus -> customerMapper.toApi((CustomerEntity) cus)).collect(Collectors.toList());
        return customers;
    }

    @GET
    @Path("/visits/all")
    @Produces({ "application/json" })
    public List<Visit> allVisits(){
        List<Visit> visits = new ArrayList<>();
        visits = VisitEntity.findAll().stream().map(cus -> visitMapper.toApi((VisitEntity) cus)).collect(Collectors.toList());
        return visits;
    }

    @GET
    @Path("/loyalty/all")
    @Produces({ "application/json" })
    public List<Loyalty> allLoyalties(){
        List<Loyalty> loyalties = new ArrayList<>();
        loyalties = LoyaltyEntity.findAll().stream().map(cus -> loyaltyMapper.toApi((LoyaltyEntity) cus)).collect(Collectors.toList());
        return loyalties;
    }

    private void incrementLoyalty(Integer id, Integer businessId){
        LoyaltyEntity loyaltyEntity = LoyaltyEntity.findLoyalty(id, businessId);
        if (loyaltyEntity == null) {
            LoyaltyEntity newLoyaltyEntity = new LoyaltyEntity(id, businessId);
            newLoyaltyEntity.persist();
        }else{
            loyaltyEntity.incrementLoyalty();
            loyaltyEntity.update();
        }
    }
}




