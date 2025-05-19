package com.service.src.DTO.Mapper;

import com.service.src.DTO.Response.CourseResponseDTO;
import com.service.src.Entity.courseEntity;

public class courseMapper {
    public static CourseResponseDTO toDto(courseEntity entity) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}