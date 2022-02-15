package com.demo.service;

import com.demo.entity.BusinessEntity;
import com.demo.repository.BusinessRepository;
import com.demo.mapper.BusinessMapper;
import com.demo.NotFoundException;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.openapitools.api.BusinessApi;
import org.openapitools.model.Business;

@Controller("/business")
@ExecuteOn(TaskExecutors.IO)
public class BusinessResource implements BusinessApi {
    private final BusinessRepository businessRepository;

    private BusinessMapper businessMapper;

    BusinessResource(BusinessRepository businessRepository, BusinessMapper businessMapper){
        this.businessRepository = businessRepository;
        this.businessMapper = businessMapper;
    }

    @Override
     public Business createBusiness() {
        BusinessEntity business = new BusinessEntity();
        businessRepository.save(business);
        return businessMapper.toApi(business);
    }

    @Override
    public Business getBusinessMetadatas(Integer id) {
        BusinessEntity business = businessRepository.findById((long) id);
        if (business == null) {
            throw new NotFoundException();
        }
        return businessMapper.toApi(business);
    }

    @Override
    public Business updateBusinessMetadatas(Integer id, Business business) {
        BusinessEntity businessEntity = businessRepository.findById((long) id);
        if (businessEntity == null) {
            throw new NotFoundException();
        }

        businessEntity.setName(business.getName());
        businessEntity.setEmail(business.getEmail());
        businessRepository.save(businessEntity);
        return businessMapper.toApi(businessEntity);
    }

    @Override
    public void deleteBusiness(Integer id) {
        BusinessEntity business = businessRepository.findById((long) id);
        if (business == null) {
            throw new NotFoundException();
        }

        businessRepository.delete(business);
    }
}
