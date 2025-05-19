package com.service.src.DTO.Response;

import lombok.Data;

import java.util.UUID;

@Data
public class CourseResponseDTO {

    private UUID id;
    private String name;
    private String code;
    private String description;
}