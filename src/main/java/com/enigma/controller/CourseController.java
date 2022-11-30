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
    public ResponseEntity getAllCourse() throws Exception {

        List<Course> courses = courseService.list();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List>("Success Get All Courses", courses));
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest course) throws Exception {

        Course newCourse = modelMapper.map(course, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success making a course", result));

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception {

        Optional<Course> course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("A course was finded", course));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") String id) throws Exception {

        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("delete success", id));

    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody Course course, @PathVariable("id") String id) throws Exception {

        courseService.update(course, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("delete success", course));

    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam String keyword, @RequestParam String value) throws Exception {

        Optional<List<Course>> result = courseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes get course by key",
                result));

    }
}
