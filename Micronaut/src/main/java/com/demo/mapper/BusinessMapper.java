package com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.entity.BusinessEntity;
import org.openapitools.model.Business;

@Mapper(componentModel = "jsr330")
public interface BusinessMapper {
    @Mapping(source = "id", target = "businessId")
    Business toApi(BusinessEntity business);
    @Mapping(source = "businessId", target = "id")
    BusinessEntity toEntity(Business business);
}
