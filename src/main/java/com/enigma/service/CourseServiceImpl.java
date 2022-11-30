package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Value("3")
    Integer dataLength;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> list() throws Exception {
        List<Course> result = courseRepository.getAll();
        if(result.isEmpty()){
            throw new NotFoundException();
        }
        return result;
    }

    @Override
    public Course create(Course course) throws Exception {
        if(courseRepository.getAll().size()<dataLength){
            throw new Exception("Data is full");
        }
        return courseRepository.create(course);
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void addBucket(String keyword, String value, List<Course> bucket, Course course) {
        if (keyword.toLowerCase().contains(value)) {
            bucket.add(course);
        }
    }

    @Override
    public Optional<List<Course>> getBy(String key, String value) throws Exception {
        List<Course> result = new ArrayList<>();
        for (Course course : list()) {
            switch (key) {
                case "title":
                    addBucket(course.getTitle(), value.toLowerCase(), result, course);
                    break;
                case "description":
                    addBucket(course.getDescription(), value.toLowerCase(), result, course);
                    break;
                case "link":
                    addBucket(course.getLink(), value.toLowerCase(), result, course);
                    break;

            }
        }
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }
}
