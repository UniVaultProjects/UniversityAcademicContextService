package com.service.src.DTO.Mapper;

import com.service.src.DTO.Response.CourseResponseDTO;
import com.service.src.DTO.Response.InstituteResponseDTO;
import com.service.src.Entity.instituteEntity;

import java.util.List;
import java.util.stream.Collectors;

public class instituteMapper {

    public static InstituteResponseDTO toDto(instituteEntity entity) {
        InstituteResponseDTO dto = new InstituteResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortname(entity.getShortname());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());

        if (entity.getCourses() != null) {
            List<CourseResponseDTO> courseDto = entity.getCourses().stream()
                    .map(courseMapper::toDto)
                    .collect(Collectors.toList());
            dto.setCourses(courseDto);
        }
        return dto;
    }
}