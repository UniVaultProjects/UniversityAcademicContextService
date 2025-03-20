package com.service.src.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "Branch")
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Branch name is required")
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank(message = "Branch code is required")
    @Size(min = 2, max = 20)
    private String code;

    @NotBlank(message = "Branch description is required")
    @Size(min = 3, max = 255)
    private String description;

    // Many branches belong to one course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
}
