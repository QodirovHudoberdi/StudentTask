package com.company.mapper;

import com.company.dto.UniversityDTO;
import com.company.entity.UniversityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversityMapper extends EntityMapping<UniversityEntity, UniversityDTO,UniversityDTO>{
}
