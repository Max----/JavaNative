package com.demo.mapper;

import com.demo.entity.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Visit;

@Mapper(componentModel = "jsr330")
public interface VisitMapper {
    @Mapping(target = "date", expression = "java(visitEntity.getDateTime().toString())")
    Visit toApi(VisitEntity visitEntity);
    VisitEntity toEntity(Visit visit);
}

