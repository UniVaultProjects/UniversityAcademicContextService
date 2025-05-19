package com.service.src.Controller;
import com.service.src.DTO.Mapper.instituteMapper;
import com.service.src.DTO.Response.InstituteResponseDTO;
import com.service.src.Entity.instituteEntity;
import com.service.src.Exceptions.Institute.instituteNotFoundException;
import com.service.src.Exceptions.Institute.instituteServiceException;
import com.service.src.Service.instituteService;
import com.service.src.Exceptions.Institute.instituteAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/institute")
public class instituteController {

    @Autowired
    private instituteService service;

    @GetMapping
    public ResponseEntity<List<InstituteResponseDTO>> getAllInstitutesController(){
        try {
            List<InstituteResponseDTO> fetchAllInstitutes = service.getAllInstitutesService();
            return new ResponseEntity<>(fetchAllInstitutes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createInstituteController(@RequestBody instituteEntity institute) {
        try {
            instituteEntity createdInstitute = service.postInstituteService(institute);
            InstituteResponseDTO responseDTO = instituteMapper.toDto(createdInstitute);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (instituteAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (instituteServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstitute(@PathVariable UUID instituteId) {
        try {
            service.deleteInstituteService(instituteId);
            return ResponseEntity.status(HttpStatus.OK).body("Institute deleted successfully");
        } catch (instituteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (instituteServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PutMapping("/{instituteId}")
    public ResponseEntity<?> updateInstituteController(@PathVariable UUID instituteId,@RequestBody instituteEntity updatedInstituteData){
        try {
           instituteEntity updatedInstitute = service.updateInstituteService(instituteId,updatedInstituteData);
           InstituteResponseDTO responseDTO = instituteMapper.toDto(updatedInstitute);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }catch (instituteNotFoundException e){
            return new ResponseEntity<>("Institute not found with id" + instituteId + " not found.",HttpStatus.BAD_REQUEST);
        }
    }

}
