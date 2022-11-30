package com.enigma.controller;

import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
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
        List<Course> courses = courseService.list();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List>("Success Get All Courses", courses));
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest course){
        Course newCourse = modelMapper.map(course, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success making a course", result));

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Course> course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("A course was finded", course));
    }

    @DeleteMapping("/{id}")
        public void deleteCourse(@PathVariable("id") String id){
        courseService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateCourse(@RequestBody Course course, @PathVariable("id") String id){
        courseService.update(course, id);
    }
}
