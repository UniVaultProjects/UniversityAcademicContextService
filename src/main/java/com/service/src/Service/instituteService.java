package com.service.src.Service;

import com.service.src.DTO.Mapper.instituteMapper;
import com.service.src.DTO.Response.InstituteResponseDTO;
import com.service.src.Entity.instituteEntity;
import com.service.src.Exceptions.Institute.instituteNotFoundException;
import com.service.src.Repository.instituteRepository;
import com.service.src.Exceptions.Institute.instituteAlreadyExistsException;
import com.service.src.Exceptions.Institute.instituteServiceException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class instituteService {

    private final instituteRepository instituteRepo;

    public instituteService(instituteRepository instituteRepo){
        this.instituteRepo = instituteRepo;
    }

    public List<InstituteResponseDTO> getAllInstitutesService(){
        try{
            List<instituteEntity> fetchedInstitutes = instituteRepo.findAll();
            return fetchedInstitutes.stream()
                    .map(instituteMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("An error occurred while fetching institutes: "+ e.getMessage());
            throw new instituteServiceException("An error occurred while fetching all institutes. Please try again later.");
        }
    }

    public instituteEntity postInstituteService(instituteEntity institute){
        try{
            Optional<instituteEntity> checkForInstituteWithName = instituteRepo.findByName(institute.getName());

            if (checkForInstituteWithName.isPresent()) {
                throw new instituteAlreadyExistsException("Institute " + institute.getName() + " already exists.");
            }

            return instituteRepo.save(institute);
        } catch (DataAccessException dae) {
            throw new instituteServiceException("An error has occurred while accessing the database.");
        }
        catch (Exception e) {
            throw new instituteServiceException("An error occurred while creating institute. Please try again later.");
        }
    }

    @Transactional
    public void deleteInstituteService(UUID instituteId){
        try{
            instituteEntity checkForInstituteWithId = instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new instituteNotFoundException("Institute with ID " + instituteId + " not found."));
            instituteRepo.deleteById(instituteId);
        } catch (DataAccessException dae) {
            throw new instituteServiceException("An error has occurred while accessing the database.");
        } catch (instituteNotFoundException e) {
            throw e; // If institute not found, rethrow the custom exception
        } catch (Exception e) {
            throw new instituteServiceException("An error occurred while deleting this institute. Please try again later.");
        }
    }

    @Transactional
    public instituteEntity updateInstituteService(UUID instituteId, instituteEntity updatedInstituteData){
        try{
            Optional<instituteEntity> checkForSpecificInstituteById = instituteRepo.findById(instituteId);

            if (checkForSpecificInstituteById.isEmpty()) {
                throw new instituteNotFoundException("Institute with ID " + instituteId + " not found.");
            }

            instituteEntity existingInstitute = checkForSpecificInstituteById.get();

            existingInstitute.setName(updatedInstituteData.getName());
            existingInstitute.setShortname(updatedInstituteData.getShortname());
            existingInstitute.setCode(updatedInstituteData.getCode());
            existingInstitute.setDescription(updatedInstituteData.getDescription());

            return instituteRepo.save(existingInstitute);

        } catch (DataAccessException dae) {
            throw new instituteServiceException("An error has occurred while accessing the database.");
        } catch (instituteNotFoundException e) {
            throw e; // If institute not found, rethrow the custom exception
        } catch (Exception e) {
            throw new instituteServiceException("An error occurred while updating this institute. Please try again later.");
        }
    }
}
