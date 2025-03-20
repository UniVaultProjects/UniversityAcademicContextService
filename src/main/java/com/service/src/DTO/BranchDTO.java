package com.service.src.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class BranchDTO {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private UUID courseId;  // Only store the course ID instead of the full entity
}