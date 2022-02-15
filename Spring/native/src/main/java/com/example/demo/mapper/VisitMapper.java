package com.example.demo.mapper;

import com.example.demo.entity.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.Visit;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    @Mapping(target = "date", expression = "java(visitEntity.getDateTime().toString())")
    Visit toApi(VisitEntity visitEntity);
    VisitEntity toEntity(Visit visit);
}

