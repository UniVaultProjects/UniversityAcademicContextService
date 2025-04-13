package com.service.src.DTO.Mapper;

import com.service.src.DTO.InstituteDTO;
import com.service.src.Entity.InstituteEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class InstituteMapper {
    private final CourseMapper courseMapper;

    public InstituteMapper(CourseMapper courseMapper){
        this.courseMapper = courseMapper;
    }

    public InstituteEntity toDTO(InstituteEntity entity){
        if(entity == null){
            return  null;
        }

        InstituteDTO dto = new InstituteDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortname(entity.getShortname());
        dto.setDescription(entity.getDescription());

        if(entity.getCourses() != null){
            dto.setCourses(entity.getCourses().stream()
                    .map(courseMapper::toDTO).toList());
        } else {
            entity.setCourses(Collections.emptyList());
        }
        return entity;
    }
}
