package com.demo.mapper;

import com.demo.entity.LoyaltyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Loyalty;

@Mapper(componentModel = "jsr330")
public interface LoyaltyMapper {
    @Mapping(target = "loyaltyCount", source = "loyaltyCounter")
    Loyalty toApi(LoyaltyEntity loyaltyEntity);
    LoyaltyEntity toEntity(Loyalty loyalty);
}
