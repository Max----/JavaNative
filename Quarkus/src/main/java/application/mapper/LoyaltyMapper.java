package application.mapper;

import application.entity.LoyaltyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Loyalty;

@Mapper(componentModel = "cdi")
public interface LoyaltyMapper {
    @Mapping(target = "loyaltyCount", source = "loyaltyCounter")
    Loyalty toApi(LoyaltyEntity loyaltyEntity);
    LoyaltyEntity toEntity(Loyalty loyalty);
}