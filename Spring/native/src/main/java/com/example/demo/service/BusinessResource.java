package com.example.demo.service;

import com.example.demo.entity.BusinessEntity;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.mapper.BusinessMapper;
import org.openapitools.api.BusinessApi;
import org.openapitools.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.NotFoundException;

@RestController
public class BusinessResource implements BusinessApi {
    private final BusinessRepository businessRepository;

    BusinessResource(BusinessRepository businessRepository){
        this.businessRepository = businessRepository;
    }

    @Autowired
    BusinessMapper businessMapper;

    @Override
    public ResponseEntity<Business> createBusiness() {
        BusinessEntity business = new BusinessEntity();
        businessRepository.save(business);
        return new ResponseEntity<Business>(businessMapper.toApi(business), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Business> getBusinessMetadatas(@PathVariable Integer id) {
        BusinessEntity business = businessRepository.findById((long) id).orElseThrow(() -> new NotFoundException());
        return new ResponseEntity<Business>(businessMapper.toApi(business), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Business> updateBusinessMetadatas(@PathVariable Integer id, @RequestBody Business business) {
        BusinessEntity businessEntity = businessRepository.findById((long) id).orElseThrow(() -> new NotFoundException());

        businessEntity.setName(business.getName());
        businessEntity.setEmail(business.getEmail());
        businessRepository.save(businessEntity);
        return new ResponseEntity<Business>(businessMapper.toApi(businessEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteBusiness(@PathVariable Integer id) {
        BusinessEntity business = businessRepository.findById((long) id).orElseThrow(() -> new NotFoundException());
        businessRepository.delete(business);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
