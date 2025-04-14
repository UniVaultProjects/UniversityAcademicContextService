package com.service.src.Controller;

import com.service.src.Entity.InstituteEntity;
import com.service.src.Exceptions.Institute.InstituteNotFoundException;
import com.service.src.Exceptions.Institute.InstituteServiceException;
import com.service.src.Service.InstituteService;
import com.service.src.Exceptions.Institute.InstituteAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/institute")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @GetMapping
    public ResponseEntity<List<InstituteEntity>> GetAllInstitutesController(){
        try {
            List<InstituteEntity> FetchAllInstitutes = instituteService.getAllInstitutesService();
            return new ResponseEntity<>(FetchAllInstitutes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> CreateInstituteController(@RequestBody InstituteEntity institute) {
        try {
            InstituteEntity createdInstitute = instituteService.postInstituteService(institute);
            return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
        } catch (InstituteAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InstituteServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstitute(@PathVariable UUID instituteId) {
        try {
            instituteService.deleteInstituteService(instituteId);
            return ResponseEntity.status(HttpStatus.OK).body("Institute deleted successfully");
        } catch (InstituteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InstituteServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<?> UpdateInstituteController(@PathVariable UUID instituteId,@RequestBody InstituteEntity updatedInstituteData){
        try {
           InstituteEntity updatedInstitute = instituteService.updateInstituteService(instituteId,updatedInstituteData);
            return new ResponseEntity<>(updatedInstitute,HttpStatus.OK);
        }catch (InstituteNotFoundException e){
            return new ResponseEntity<>("Institute not found with id" + instituteId + " not found.",HttpStatus.BAD_REQUEST);
        }
    }

}
