package application.mapper;

import application.entity.BusinessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Business;

@Mapper(componentModel = "cdi")
public interface BusinessMapper {
    @Mapping(source = "id", target = "businessId")
    Business toApi(BusinessEntity business);
    @Mapping(source = "businessId", target = "id")
    BusinessEntity toEntity(Business business);
}