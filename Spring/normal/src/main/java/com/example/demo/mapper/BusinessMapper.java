package com.example.demo.mapper;

import com.example.demo.entity.BusinessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Business;

@Mapper(componentModel = "spring")
public interface BusinessMapper {
    @Mapping(source = "id", target = "businessId")
    Business toApi(BusinessEntity business);
    @Mapping(source = "businessId", target = "id")
    BusinessEntity toEntity(Business business);
}
