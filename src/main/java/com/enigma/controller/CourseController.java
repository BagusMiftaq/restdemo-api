package com.enigma.controller;

import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
import com.enigma.model.response.ErrorResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public  ResponseEntity getAllCourse(){
        try {
            List<Course> courses = courseService.list();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessResponse<List>("Success Get All Courses", courses));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Not Found"));
        }
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest course){
        try {
            Course newCourse = modelMapper.map(course, Course.class);
            Course result = courseService.create(newCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success making a course", result));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Not Found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        try {
            Optional<Course> course = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("A course was finded", course));

         } catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Not Found"));
        }
     }

    @DeleteMapping("/{id}")
        public ResponseEntity deleteCourse(@PathVariable("id") String id){
        try {
            courseService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("delete success", id));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Not Found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody Course course, @PathVariable("id") String id){
       try {
           courseService.update(course, id);
           return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("delete success", course));

       } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Not Found"));
        }
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam String keyword, @RequestParam String value){
        try {
           List<Course> result = courseService.getBy(keyword, value);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes get course by key",
                    result));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X02", "Not Found"));
        }
    }
}
