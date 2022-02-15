package com.example.demo.mapper;

import com.example.demo.entity.LoyaltyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Loyalty;

@Mapper(componentModel = "spring")
public interface LoyaltyMapper {
    @Mapping(target = "loyaltyCount", source = "loyaltyCounter")
    Loyalty toApi(LoyaltyEntity loyaltyEntity);
    LoyaltyEntity toEntity(Loyalty loyalty);
}
