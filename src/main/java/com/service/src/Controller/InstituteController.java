package com.service.src.Controller;

import com.service.src.Entity.InstituteEntity;
import com.service.src.Service.InstituteService;
import com.service.src.Utils.Institute.InstituteAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uni")
public class InstituteController {
    private final InstituteService instituteService;

    public InstituteController(InstituteService instituteService){
        this.instituteService = instituteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<InstituteEntity>> getAllUniversitiesController(){
       List<InstituteEntity> allUniversities = this.instituteService.getInstitutes();
       return new ResponseEntity<>(allUniversities,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUniversityController(@RequestBody @Valid InstituteEntity instituteEntity){
        try {
            // Attempt to create the university
            InstituteEntity createdEntity = instituteService.createInstitute(instituteEntity);

            // Return a 201 Created response if the university is successfully created
            return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);

        } catch (InstituteAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)  // 400 Bad Request status
                    .body("Error: " + e.getMessage());  // Custom error message in the body
        }
    }
}
