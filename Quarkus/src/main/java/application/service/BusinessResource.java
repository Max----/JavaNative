package application.service;

import application.entity.BusinessEntity;
import application.mapper.BusinessMapper;
import org.openapitools.api.BusinessApi;
import org.openapitools.model.Business;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;

@ApplicationScoped
@Path("business")
public class BusinessResource implements BusinessApi {

    @Inject
    BusinessMapper businessMapper;

    @Override
    @Transactional
    public Business createBusiness() {
        BusinessEntity business = new BusinessEntity();
        business.persist();
        return businessMapper.toApi(business);
    }

    @Override
    public Business getBusinessMetadatas(Integer id) {
        BusinessEntity b = BusinessEntity.findById((long)id);
        if (b == null) {
            throw new NotFoundException();
        }

        return businessMapper.toApi(b);
    }

    @Override
    @Transactional
    public Business updateBusinessMetadatas(Integer id, Business business) {
        BusinessEntity entity = BusinessEntity.findById((long)id);
        if(entity == null) {
            throw new NotFoundException();
        }

        entity.setName(business.getName());
        entity.setEmail(business.getEmail());
        entity.persist();
        return businessMapper.toApi(entity);
    }

    @Override
    @Transactional
    public void deleteBusiness(Integer id) {
        BusinessEntity entity = BusinessEntity.findById((long)id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
}
