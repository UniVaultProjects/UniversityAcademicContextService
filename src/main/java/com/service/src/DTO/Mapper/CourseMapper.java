package com.service.src.DTO.Mapper;

import com.service.src.DTO.CourseDTO;
import com.service.src.Entity.CourseEntity;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class CourseMapper {

    private final BranchMapper branchMapper;

    public CourseMapper(BranchMapper branchMapper) {
        this.branchMapper = branchMapper;
    }

    public CourseDTO toDTO(CourseEntity entity) {
        if (entity == null) {
            return null;
        }

        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortname(entity.getShortname());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        // Extract only the institute ID for the DTO
        if (entity.getInstitute() != null) {
            dto.setInstituteId(entity.getInstitute().getId());
        }

        // Map branch entities to DTOs
        if (entity.getBranches() != null) {
            dto.setBranches(entity.getBranches().stream()
                    .map(branchMapper::toDTO)
                    .collect(Collectors.toList()));
        } else {
            dto.setBranches(Collections.emptyList());
        }

        return dto;
    }
}