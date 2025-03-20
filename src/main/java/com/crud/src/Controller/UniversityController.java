package com.crud.src.Controller;

import com.crud.src.Entity.UniversityEntity;
import com.crud.src.Service.UniversityService;
import com.crud.src.Utils.UniversityAlreadyExistsException;
import com.crud.src.Utils.UniversityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/uni")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService){
        this.universityService = universityService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UniversityEntity>> getAllUniversitiesController(){
       List<UniversityEntity> allUniversities = this.universityService.getUniversities();
       return new ResponseEntity<>(allUniversities,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUniversityController(@RequestBody @Valid UniversityEntity universityEntity){
        try {
            // Attempt to create the university
            UniversityEntity createdEntity = universityService.createUniversity(universityEntity);

            // Return a 201 Created response if the university is successfully created
            return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);

        } catch (UniversityAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)  // 400 Bad Request status
                    .body("Error: " + e.getMessage());  // Custom error message in the body
        }
    }

    @DeleteMapping("/delete/{Id}")
    public boolean deleteUniversityController(@PathVariable UUID Id) {
        // Check if the university exists and delete it
        boolean isDeleted = universityService.deleteUniversity(Id);
        if (isDeleted) {
            return true;
        } else {
            throw new UniversityNotFoundException("University with ID " + Id + " not found");  // 404 Not Found
        }
    }
}
