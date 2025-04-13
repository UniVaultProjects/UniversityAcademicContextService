package com.service.src.Controller;

import com.service.src.Entity.InstituteEntity;
import com.service.src.Service.InstituteService;
import com.service.src.Exceptions.Institute.InstituteAlreadyExistsException;
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
    public ResponseEntity<List<InstituteEntity>> GetAllInstitutesController(){
        try {
            List<InstituteEntity> FetchAllInstitutes = instituteService.GetAllInstitutesService();
            return new ResponseEntity<>(FetchAllInstitutes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
