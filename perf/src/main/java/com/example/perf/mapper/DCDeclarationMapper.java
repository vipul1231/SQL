package com.example.perf.mapper;


import com.example.perf.dto.DeclarationDto;
import com.example.perf.entity.DCDeclarationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DCDeclarationMapper {
    DCDeclarationMapper INSTANCE = Mappers.getMapper(DCDeclarationMapper.class);

    DeclarationDto dcDeclarationEntityToDto(DCDeclarationEntity dcDeclarationEntities);

    List<DCDeclarationEntity> declarationEntityListToDto(List<DeclarationDto> declarationDto);
}
