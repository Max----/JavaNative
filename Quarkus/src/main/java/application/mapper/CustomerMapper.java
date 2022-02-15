package application.mapper;

import application.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Customer;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {
    @Mapping(source = "id", target = "customerId")
    Customer toApi(CustomerEntity customer);
    @Mapping(source = "customerId", target = "id")
    CustomerEntity toEntity(Customer customer);
}