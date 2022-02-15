package application.mapper;

import application.entity.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Visit;

@Mapper(componentModel = "cdi")
public interface VisitMapper {
    @Mapping(target = "date", expression = "java(visitEntity.getDateTime().toString())")
    Visit toApi(VisitEntity visitEntity);
    VisitEntity toEntity(Visit visit);
}

