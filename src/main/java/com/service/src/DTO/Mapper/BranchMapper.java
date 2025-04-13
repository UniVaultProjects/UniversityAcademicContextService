package com.service.src.DTO.Mapper;

import com.service.src.DTO.BranchDTO;
import com.service.src.Entity.BranchEntity;

public class BranchMapper {
    public BranchDTO toDTO(BranchEntity entity) {
        if (entity == null) {
            return null;
        }

        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        // Extract only the course ID for the DTO
        if (entity.getCourse() != null) {
            dto.setCourseId(entity.getCourse().getId());
        }

        return dto;
    }

}
