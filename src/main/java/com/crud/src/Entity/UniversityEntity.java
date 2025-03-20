package com.crud.src.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="University")
public class UniversityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "University name is required")
    @Size(min=3,max=100)
    private String name;

    @Size(min=3,max=100)
    @NotBlank(message = "University shortname is require")
    private String shortname;

    @Size(min=3,max=20)
    @NotBlank(message = "University code is required")
    private String code;

}
