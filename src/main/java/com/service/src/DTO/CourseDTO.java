package com.service.src.DTO;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CourseDTO {
    private UUID id;
    private String name;
    private String shortname;
    private String code;
    private String description;
    private UUID instituteId;  // Only store the institute ID instead of the full entity
    private List<BranchDTO> branches;  // Include branch details
}