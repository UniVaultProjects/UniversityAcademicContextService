package com.service.src.Service;

import com.service.src.DTO.Mapper.courseMapper;
import com.service.src.DTO.Response.CourseResponseDTO;
import com.service.src.Entity.courseEntity;
import com.service.src.Exceptions.Course.courseAlreadyExistsException;
import com.service.src.Exceptions.Course.courseNotFoundException;
import com.service.src.Exceptions.Course.courseServiceException;
import com.service.src.Exceptions.Institute.instituteNotFoundException;
import com.service.src.Exceptions.Institute.instituteServiceException;
import com.service.src.Repository.courseRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class courseService {
    private final courseRepository courseRepo;

    public courseService(courseRepository courseRepo){
        this.courseRepo = courseRepo;
    }

    public List<CourseResponseDTO> getAllCoursesService(){
        try{
            List<courseEntity> fetchedCourses = courseRepo.findAll();
            return fetchedCourses.stream()
                    .map(courseMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Course service error at exception "+ e.getMessage());
            throw new instituteServiceException("An error occurred while fetching all courses. Please try again later.");
        }
    }

    public courseEntity postCourseService(courseEntity course){
        try{
            Optional<courseEntity> checkForCourseWithName = courseRepo.findByName(course.getName());

            if(checkForCourseWithName.isPresent()){
                throw new courseAlreadyExistsException("Course " + course.getName() + " already exists.");
            }

            return courseRepo.save(course);
        } catch (DataAccessException dae) {
            System.err.println("Course service error at DataAccessException "+ dae.getMessage());
            throw new courseServiceException("An error has occurred while accessing the database.");
        }
        catch (Exception e) {
            System.err.println("Course service error at exception "+ e.getMessage());
            throw new instituteServiceException("An error occurred while creating course. Please try again later.");
        }
    }

    @Transactional
    public void deleteCourse(UUID courseId){
        try{
            courseEntity checkForCourseWithId = courseRepo.findById(courseId)
                    .orElseThrow(()->new courseNotFoundException("Course with Id " + courseId + " not found."));
            courseRepo.deleteById(courseId);
        } catch (DataAccessException dae) {
            System.err.println("Course service error at DataAccessException "+ dae.getMessage());
            throw new courseServiceException("An error has occurred while accessing the database.");
        } catch (courseNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new instituteServiceException("An error occurred while deleting this institute. Please try again later.");
        }
    }

    @Transactional
    public CourseResponseDTO updateCourse(UUID courseId, courseEntity updatedCourseData){
        try{
            Optional<courseEntity> checkForSpecificCourseWithId = courseRepo.findById(courseId);

            if(checkForSpecificCourseWithId.isEmpty()){
                throw new courseNotFoundException("Course with Id " + courseId + " not found.");
            }

            courseEntity existingCourse = checkForSpecificCourseWithId.get();

            existingCourse.setName(updatedCourseData.getName());
            existingCourse.setShortname(updatedCourseData.getShortname());
            existingCourse.setCode(updatedCourseData.getCode());
            existingCourse.setDescription(updatedCourseData.getDescription());

            courseEntity updatedCourse = courseRepo.save(existingCourse);
            return courseMapper.toDto(updatedCourse);
        }catch (DataAccessException dae) {
            System.err.println("Course service error at DataAccessException "+ dae.getMessage());
            throw new courseServiceException("An error has occurred while accessing the database.");
        }catch (courseNotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new instituteServiceException("An error occurred while deleting this institute. Please try again later.");
        }
    }

}
