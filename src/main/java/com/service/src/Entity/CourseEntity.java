package com.service.src.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="Course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Course name is required")
    @Size(min=3,max=100)
    private String name;

    @Size(min=3,max=100)
    @NotBlank(message = "Course shortname is required")
    private String shortname;

    @Size(min=3,max=20)
    @NotBlank(message = "Course code is required")
    private String code;

    @Size(min=3,max=100)
    @NotBlank(message = "Course description is required")
    private String description;

    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    private InstituteEntity institute;

//   @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//   private List<BranchEntity> branches;

}
