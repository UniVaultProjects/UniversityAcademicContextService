package com.service.src.DTO.Response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class InstituteResponseDTO {
    private UUID id;
    private String name;
    private String shortname;
    private String code;
    private String description;
    private List<CourseResponseDTO> courses;
}