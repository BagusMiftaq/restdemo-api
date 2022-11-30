package com.enigma.service;

import com.enigma.model.Course;
import com.enigma.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> list() {
        try {
            return courseRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return courseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public Optional<List<Course>> getBy(String key, String value) {
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
