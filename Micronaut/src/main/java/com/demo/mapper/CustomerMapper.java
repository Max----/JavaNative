package com.demo.mapper;

import com.demo.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Customer;

@Mapper(componentModel = "jsr330")
public interface CustomerMapper {
    @Mapping(source = "id", target = "customerId")
    Customer toApi(CustomerEntity customer);
    @Mapping(source = "customerId", target = "id")
    CustomerEntity toEntity(Customer customer);
}
